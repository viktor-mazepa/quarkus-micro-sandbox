package com.mazasoft.service.number;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasKey;

@QuarkusTest
public class IsbnNumberResourceTest {

    @Test
    public void shouldReturnNumberDto() {
        given()
                .when().header("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIGRldGFpbHMiLCJzZXJ2aWNlX2tleSI6InJLY3JLN2RWcm1iQk4zYmpwZEJncDFnZFRjbmY0U3hDTllSVDJhZ0tub2c9Iiwic2VydmljZV9uYW1lIjoiYm9va19zZXJ2aWNlIiwiaXNzIjoiaHR0cHM6Ly9leGFtcGxlLmNvbS9pc3N1ZXIiLCJpYXQiOjE2ODIyODY2ODl9.FBJ3-jyxk1SF0eCVS4Dhoaxt8DH1rZD5g1OV-B6yt0E")
                .get("/api/numbers")
                .then()
                .statusCode(200)
                .body("isbn_10", startsWith("10-"))
                .body("isbn_13", startsWith("13-"))
                .body(not(hasKey("generationDate")));
    }
}