package com.mazasoft.service.number;


import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
@OpenAPIDefinition(info = @Info(
        title = "Number microservice",
        description = "This microservice generates book ISBN numbers",
        version = "1.0",
        contact = @Contact(
                name = "Viktor Mazepa",
                url = "https://www.linkedin.com/in/viktor-mazepa/")),
        externalDocs = @ExternalDocumentation(url = "https://github.com/viktor-mazepa", description = "All microservices code"),
        tags = {
                @Tag(name = "api", description = "Public API"),
                @Tag(name = "numbers", description = "")
        })
public class NumberMicroservice extends Application {

}
