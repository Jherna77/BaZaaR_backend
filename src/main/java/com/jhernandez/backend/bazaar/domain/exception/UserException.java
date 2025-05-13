package com.jhernandez.backend.bazaar.domain.exception;

public class UserException extends DomainException {
    
    public UserException(String message) {
        super(message);
    }

    // public UserException(Integer code) {
    //     obtener de messages.properties el mensaje de error correspondiente al código
    // }

}
