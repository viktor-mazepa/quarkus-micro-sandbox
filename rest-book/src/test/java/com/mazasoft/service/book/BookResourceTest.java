package com.mazasoft.service.book;

import com.mazasoft.service.book.model.Book;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class BookResourceTest {

    private static final String TEST_TITLE = "Test title";
    private static final String TEST_AUTHOR = "Test author";
    private static final String TEST_GENRE = "Test genre";
    private static final Integer TEST_YEAR_OF_PUBLICATION = 2023;

    private final Map<String,Object> PARAMETERS = new HashMap<>(){{
        put("title", TEST_TITLE);
        put("author", TEST_AUTHOR);
        put("yearOfPublication", TEST_YEAR_OF_PUBLICATION);
        put("genre", TEST_GENRE);
    }};

    @Test
    @TestTransaction
    public void testCreateBookEndpoint() {
        long countBefore = Book.count();
        given().formParams(PARAMETERS)
                .when().post("/api/books")
                .then()
                .statusCode(201)
                .body("isbn_13", startsWith("13-"))
                .body("title", is(TEST_TITLE))
                .body("author", is(TEST_AUTHOR))
                .body("year_of_publication", is(TEST_YEAR_OF_PUBLICATION))
                .body("genre", is(TEST_GENRE))
        ;
        assertEquals(countBefore + 1, Book.count());
    }

}