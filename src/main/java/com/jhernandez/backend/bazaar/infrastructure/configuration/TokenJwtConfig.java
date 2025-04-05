package com.jhernandez.backend.bazaar.infrastructure.configuration;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;

/**
 * TokenJwtConfig class is responsible for defining the configuration for JWT tokens.
 * It includes the secret key used for signing the tokens, the prefix for the token, and the header name for authorization.
 */
public class TokenJwtConfig {

    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE_JSON = "application/json";
}
