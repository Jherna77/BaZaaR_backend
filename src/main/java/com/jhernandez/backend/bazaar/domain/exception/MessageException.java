package com.jhernandez.backend.bazaar.domain.exception;

public class MessageException extends DomainException {

    public MessageException(ErrorCode errorCode) {
        super(errorCode);
    }
}
