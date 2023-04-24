package com.mazasoft.service.book;

import com.mazasoft.service.book.dto.IsbnDto;
import com.mazasoft.service.book.proxy.NumberProxy;
import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.HeaderParam;

@Mock
@RestClient
public class MockNumberProxy implements NumberProxy {
    @Override
    public IsbnDto generateIsbnNumbers(@HeaderParam("Authorization") String authorizationHeader) {
        IsbnDto isbnDto = new IsbnDto();
        isbnDto.isbn13 = "13-mock";
        isbnDto.isbn10="10-mock";
        return isbnDto;
    }
}
