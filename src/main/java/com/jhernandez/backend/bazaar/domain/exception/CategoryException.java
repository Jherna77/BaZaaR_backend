package com.jhernandez.backend.bazaar.domain.exception;

public class CategoryException extends DomainException {

    public CategoryException(ErrorCode errorCode) {
        super(errorCode);
    }

}
