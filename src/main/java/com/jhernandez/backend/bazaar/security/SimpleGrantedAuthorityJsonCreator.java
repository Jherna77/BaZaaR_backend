package com.jhernandez.backend.bazaar.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityJsonCreator {
    // This class is used to deserialize the JSON object into a SimpleGrantedAuthority object
    // when the JWT token is parsed. The @JsonCreator annotation indicates that this constructor should be used to create an instance of the class when deserializing JSON.
    // @JsonCreator
    // public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role) {}

}
