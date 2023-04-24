package com.mazasoft.service.book;

import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Application;

public class BookMicroService extends Application {

    @ApplicationScoped
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
