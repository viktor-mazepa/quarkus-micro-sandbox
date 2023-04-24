package com.mazasoft.service.number.proxy;

import com.mazasoft.service.common.dto.TokenDto;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RegisterRestClient(configKey = "auth.proxy")
@Path("/api/token")
public interface AuthProxy {

    @POST
    @Path("/validation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response validateToken(TokenDto tokenDto);
}
