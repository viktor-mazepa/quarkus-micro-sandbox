package com.mazasoft.service.common.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

public class TokenDto {

    @JsonbProperty("service_name")
    @Schema(required = true)
    private String serviceName;
    @Schema(required = false)
    private String token;

    public TokenDto() {
    }

    public TokenDto(String serviceName, String token) {
        this.serviceName = serviceName;
        this.token = token;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
