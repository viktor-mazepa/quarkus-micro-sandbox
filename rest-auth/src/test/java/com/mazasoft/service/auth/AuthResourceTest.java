package com.mazasoft.service.auth;

import com.mazasoft.service.auth.common.KeyHolder;
import io.quarkus.test.junit.QuarkusTest;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.regex.Pattern;

import static com.google.common.base.Predicates.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@QuarkusTest
public class AuthResourceTest {

    @Inject
    protected AuthMicroService authMicroService;

    @Inject
    protected KeyHolder keyHolder;

    JSONObject jsonObj = new JSONObject()
            .put("key", "rKcrK7dVrmbBN3bjpdBgp1gdTcnf4SxCNYRT2agKnog=")
            .put("service_name", "book_service");

    @Test
    public void testGenerateTokenEndpoint() {
        given()
                .contentType("application/json")  //another way to specify content type
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post("/api/token")
                .then()
                .statusCode(201)
                .body(not(hasKey("token")));
    }

    @Test
    public void testKeyHolder() {
        authMicroService.onStart(null);
        assertNotEquals(keyHolder.getSize(), 0);
    }
}