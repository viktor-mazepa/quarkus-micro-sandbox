package com.mazasoft.service.auth.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mazasoft.service.auth.exceptions.SecretException;
import com.mazasoft.service.auth.exceptions.TokenIncorrectException;
import com.mazasoft.service.auth.exceptions.UnathorizedServiceException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;

@ApplicationScoped
public class JwtUtils {

    @ConfigProperty(name = "auth.issuer", defaultValue = "https://example.com/issuer")
    protected String issuer;
    @ConfigProperty(name = "auth.secret")
    protected String secret;
    private static final String SERVICE_DETAILS = "User details";
    private static final String SERVICE_NAME = "service_name";
    private static final String SERVICE_KEY = "service_key";
    private final KeyHolder keyHolder;

    @Inject
    public JwtUtils(KeyHolder keyHolder) {
        this.keyHolder = keyHolder;
    }

    public String generateJWT(String key, String serviceName) throws Exception {
        if (!keyHolder.containsKey(key)) {
            throw new UnathorizedServiceException();
        }
        if (secret == null) {
            throw new SecretException();
        }
        return JWT.create()
                .withSubject(SERVICE_DETAILS)
                .withClaim(SERVICE_NAME, serviceName)
                .withClaim(SERVICE_KEY, key)
                .withIssuedAt(new Date())
                .withIssuer(issuer)
                .sign(Algorithm.HMAC256(secret))
                ;
    }

    public void validateToken(String token) throws JWTVerificationException, TokenIncorrectException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(SERVICE_DETAILS)
                .withIssuer(issuer)
                .build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        String key = decodedJWT.getClaim(SERVICE_KEY).asString();
        if (!keyHolder.containsKey(key)) {
            throw new TokenIncorrectException();
        }
    }
}
