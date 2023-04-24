package com.mazasoft.service.number;

import com.mazasoft.service.common.dto.TokenDto;
import com.mazasoft.service.number.proxy.AuthProxy;
import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.core.Response;

@Mock
@RestClient
public class AuthProxyMock implements AuthProxy {
    @Override
    public Response validateToken(TokenDto tokenDto) {
        return Response.status(202).build();
    }
}
