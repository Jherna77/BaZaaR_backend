package com.jhernandez.backend.bazaar.domain.exception;

public class ProductException extends DomainException {

    public ProductException(ErrorCode errorCode) {
        super(errorCode);
    }

}
