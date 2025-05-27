package com.jhernandez.backend.bazaar.domain.exception;

public class PaymentException extends DomainException {

    public PaymentException(ErrorCode errorCode) {
        super(errorCode);
    }

}
