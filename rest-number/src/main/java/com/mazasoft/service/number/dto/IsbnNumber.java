package com.mazasoft.service.number.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.time.Instant;

@Schema(description = "Several ISBN format")
public class IsbnNumber {

    @JsonbProperty("isbn_10")
    @Schema(required = true)
    private String isbn10;

    @JsonbProperty("isbn_13")
    @Schema(required = true)
    private String isbn13;

    @JsonbTransient
    private final Instant generationDate;

    @JsonbProperty("error_message")
    private String errorMessage;

    public IsbnNumber() {
        this.generationDate = Instant.now();
    }


    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }


    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public Instant getGenerationDate() {
        return generationDate;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "IsbnNumber{" +
                "isbn10='" + isbn10 + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", generationDate=" + generationDate +
                '}';
    }
}
