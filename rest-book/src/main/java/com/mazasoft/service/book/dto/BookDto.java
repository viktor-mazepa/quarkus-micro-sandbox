package com.mazasoft.service.book.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;
import java.time.Instant;

@Schema(description = "A book")
public class BookDto implements Serializable {
    @JsonbProperty("isbn_13")
    @Schema(required = true)
    private String isbn13;

    @Schema(required = true)
    private String title;

    @Schema(required = true)
    private String author;

    @JsonbProperty("year_of_publication")
    @Schema(required = true)
    private int yearOfPublication;

    private String genre;

    @JsonbProperty("generation_date")
    @Schema(implementation = String.class, format = "date")
    private Instant generationDate;

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Instant getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(Instant generationDate) {
        this.generationDate = generationDate;
    }
}
