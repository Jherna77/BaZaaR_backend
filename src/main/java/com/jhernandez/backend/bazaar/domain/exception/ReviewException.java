package com.jhernandez.backend.bazaar.domain.exception;

public class ReviewException extends DomainException {

    public ReviewException(ErrorCode errorCode) {
        super(errorCode);
    }

}
