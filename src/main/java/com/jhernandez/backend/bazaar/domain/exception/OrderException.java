package com.jhernandez.backend.bazaar.domain.exception;

public class OrderException extends DomainException {

    public OrderException(ErrorCode errorCode) {
        super(errorCode);
    }

}
