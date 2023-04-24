package com.mazasoft.service.auth.resource;

import com.mazasoft.service.auth.common.JwtUtils;

import com.mazasoft.service.common.dto.AuthDto;
import com.mazasoft.service.common.dto.ErrorDto;
import com.mazasoft.service.common.dto.TokenDto;
import org.jboss.logging.Logger;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/token")
public class AuthResource {

    protected final JwtUtils jwtUtils;
    protected final Logger logger;

    @Inject
    public AuthResource(JwtUtils jwtUtils, Logger logger) {
        this.jwtUtils = jwtUtils;
        this.logger = logger;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTokenForService(AuthDto authDto) {
        try {
            String jwt = jwtUtils.generateJWT(authDto.getKey(), authDto.getServiceName());
            TokenDto tokenDto = new TokenDto(authDto.getServiceName(), jwt);
            return Response.status(201).entity(tokenDto).build();
        } catch (Exception e) {
            return Response.status(401).entity(new ErrorDto(e.getMessage())).build();
        }
    }

    @POST
    @Path("/validation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateToken(TokenDto tokenDto) {
        try {
            jwtUtils.validateToken(tokenDto.getToken());
            return Response.status(202).build();
        } catch (Exception e) {
            return Response.status(401).entity(new ErrorDto(e.getMessage())).build();
        }
    }


}