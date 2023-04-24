package com.mazasoft.service.number.resource;

import com.mazasoft.service.number.dto.IsbnNumber;
import com.mazasoft.service.common.dto.TokenDto;
import com.mazasoft.service.number.proxy.AuthProxy;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Random;

@Path("/api/numbers")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Number REST Endpoint")
public class NumberResource {

    private final Logger logger;

    @Inject
    @RestClient
    protected AuthProxy authProxy;

    @Inject
    public NumberResource(Logger logger) {
        this.logger = logger;
    }

    @GET
    @Operation(summary = "Generates book numbers", description = "ISBN 13 and ISBN 10 numbers")
    public IsbnNumber generateIsbnNumber(@HeaderParam("Authorization") String authorizationHeader) {
        if (!validateToken(authorizationHeader)) {
            IsbnNumber isbnNumber = new IsbnNumber();
            isbnNumber.setErrorMessage("Unauthorized client");
            return isbnNumber;
        }

        IsbnNumber isbnNumber = new IsbnNumber();
        isbnNumber.setIsbn10("10-" + new Random().nextInt(100_000));
        isbnNumber.setIsbn13("13-" + new Random().nextInt(100_000_000));
        return isbnNumber;
    }

    private boolean validateToken(String authorizationHeader) {
        if (authorizationHeader == null)
            return false;
        String jwt = authorizationHeader.substring("Bearer ".length());
        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(jwt);
        try (Response response = authProxy.validateToken(tokenDto)) {
            return response.getStatus() == 202;
        } catch (ResteasyWebApplicationException ex) {
            return false;
        }
    }
}