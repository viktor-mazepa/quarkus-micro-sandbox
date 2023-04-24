package com.mazasoft.service.common.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

@Schema(description = "Request for token generation")
public class AuthDto {

    @Schema(required = true)
    private String key;
    @JsonbProperty("service_name")
    @Schema(required = true)
    private String serviceName;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
