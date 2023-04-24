package com.mazasoft.service.book;

import com.mazasoft.service.common.dto.AuthDto;
import com.mazasoft.service.common.dto.TokenDto;
import com.mazasoft.service.book.proxy.AuthProxy;

import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Mock
@RestClient
public class MockAuthProxy implements AuthProxy {

    @Override
    public TokenDto generateToken(AuthDto authDto) {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setServiceName("book_service");
        tokenDto.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIGRldGFpbHMiLCJzZXJ2aWNlX2tl");
        return tokenDto;
    }
}
