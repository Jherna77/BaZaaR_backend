package com.jhernandez.backend.bazaar.domain.exception;

public class UserException extends DomainException {

    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }

}
