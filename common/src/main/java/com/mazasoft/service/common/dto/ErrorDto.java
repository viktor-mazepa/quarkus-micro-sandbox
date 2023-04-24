package com.mazasoft.service.common.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

@Schema(description = "Error response")
public class ErrorDto {
    @Schema(required = true)
    @JsonbProperty("error_message")
    private String errorMessage;

    public ErrorDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorDto() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
