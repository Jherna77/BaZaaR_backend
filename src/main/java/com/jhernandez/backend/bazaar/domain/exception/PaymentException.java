package com.jhernandez.backend.bazaar.domain.exception;

import com.jhernandez.backend.bazaar.domain.model.ErrorCode;

public class PaymentException extends DomainException {

    public PaymentException(ErrorCode errorCode) {
        super(errorCode);
    }

}
