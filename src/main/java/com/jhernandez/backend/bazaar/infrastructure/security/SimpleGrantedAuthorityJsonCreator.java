package com.jhernandez.backend.bazaar.infrastructure.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * This class is used to deserialize the JSON object into a SimpleGrantedAuthority object
 * when the JWT token is parsed. The @JsonCreator annotation indicates that this constructor should be used to create an instance of the class when deserializing JSON.
 */
public abstract class SimpleGrantedAuthorityJsonCreator {

    @JsonCreator
    public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role) {}

}
