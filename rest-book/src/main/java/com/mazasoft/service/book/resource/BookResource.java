package com.mazasoft.service.book.resource;

import com.mazasoft.service.book.dto.*;
import com.mazasoft.service.common.dto.AuthDto;
import com.mazasoft.service.common.dto.TokenDto;
import com.mazasoft.service.common.dto.ErrorDto;

import com.mazasoft.service.book.model.Book;
import com.mazasoft.service.book.proxy.AuthProxy;
import com.mazasoft.service.book.proxy.NumberProxy;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Number REST Endpoint")
public class BookResource {

    private final Logger logger;

    private final ModelMapper modelMapper;

    @Inject
    @RestClient
    protected NumberProxy numberProxy;

    @Inject
    @RestClient
    protected AuthProxy authProxy;

    @ConfigProperty(name = "auth.service.secret")
    protected String secret;

    @ConfigProperty(name = "auth.service.name")
    protected String serviceName;


    @Inject
    public BookResource(Logger logger, ModelMapper modelMapper) {
        this.logger = logger;
        this.modelMapper = modelMapper;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Retry(maxRetries = 3, delay = 2000)
    @Fallback(fallbackMethod = "fallbackOnCreatingABook")
    @Transactional
    @Operation(summary = "Store book information to the database", description = "Create a book with ISBN number")
    public Response createABook(@FormParam("title") String title, @FormParam("author") String author, @FormParam("yearOfPublication") int yearOfPublication, @FormParam("genre") String genre) {
        Book book = new Book();
        book.author = author;
        String authHeader = generateHeader();
        IsbnDto isbnDto = numberProxy.generateIsbnNumbers(authHeader);
        if (isbnDto.errorMessage != null) {
            return Response.status(500).entity(new ErrorDto(isbnDto.errorMessage)).build();
        }
        book.isbn13 = isbnDto.isbn13;
        book.title = title;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        Book.persist(book);
        return Response.status(201).entity(convertToBookDTO(book)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all book records from the database", description = "Fetch all books")
    public Collection<BookDto> getAllBooks() {
        return Book.findAll().stream().map(entity -> convertToBookDTO((Book) entity)).collect(Collectors.toList());
    }

    private BookDto convertToBookDTO(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    private String generateHeader() {
        AuthDto authDto = new AuthDto();
        authDto.setKey(secret);
        authDto.setServiceName(serviceName);
        TokenDto tokenDto = authProxy.generateToken(authDto);
        return "Bearer " + tokenDto.getToken();
    }

    public Response fallbackOnCreatingABook(String title, String author, int yearOfPublication, String genre) throws FileNotFoundException {
        Book book = new Book();
        book.isbn13 = "Will be set later";
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        saveBookOnDisk(book);
        logger.info("Book saved on disk: " + book);
        return Response.status(206).entity(book).build();
    }

    private void saveBookOnDisk(Book book) throws FileNotFoundException {
        String bookJson = JsonbBuilder.create().toJson(book);
        try (PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")) {
            out.println(bookJson);
        }
    }
}