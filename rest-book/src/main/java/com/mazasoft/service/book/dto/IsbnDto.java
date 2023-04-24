package com.mazasoft.service.book.dto;

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;

public class IsbnDto implements Serializable {
    @JsonbProperty("isbn_13")
    public String isbn13;

    @JsonbProperty("isbn_10")
    public String isbn10;

    @JsonbProperty("error_message")
    public String errorMessage;
}
