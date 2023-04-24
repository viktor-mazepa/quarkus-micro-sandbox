package com.mazasoft.service.auth;

import com.mazasoft.service.auth.common.KeyHolder;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@ApplicationScoped
public class AuthMicroService {

    protected final KeyHolder keyHolder;

    @ConfigProperty(name = "auth.keystore.file", defaultValue = "/keystore/book.key")
    protected String keyStoreFile;

    protected final Logger logger;

    @Inject
    public AuthMicroService(KeyHolder keyHolder, Logger logger) {
        this.keyHolder = keyHolder;
        this.logger = logger;
    }

    void onStart(@Observes StartupEvent ev) {
        try {
            try (InputStream inputStream = getClass().getResourceAsStream(keyStoreFile); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line = reader.readLine();
                while (line != null) {
                    keyHolder.addKey(line);
                    line = reader.readLine();
                }
            }
        } catch (Exception ex) {
            logger.error("Error during keystore parsing: ", ex);
        }
    }
}
