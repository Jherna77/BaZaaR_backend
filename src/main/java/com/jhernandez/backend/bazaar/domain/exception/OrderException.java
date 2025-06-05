package com.jhernandez.backend.bazaar.domain.exception;

import com.jhernandez.backend.bazaar.domain.model.ErrorCode;

public class OrderException extends DomainException {

    public OrderException(ErrorCode errorCode) {
        super(errorCode);
    }

}
