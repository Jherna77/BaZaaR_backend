package com.jhernandez.backend.bazaar.domain.exception;

import com.jhernandez.backend.bazaar.domain.model.ErrorCode;

public class ProductException extends DomainException {

    public ProductException(ErrorCode errorCode) {
        super(errorCode);
    }

}
