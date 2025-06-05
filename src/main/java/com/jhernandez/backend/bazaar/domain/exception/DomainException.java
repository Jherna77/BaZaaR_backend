package com.jhernandez.backend.bazaar.domain.exception;

import com.jhernandez.backend.bazaar.domain.model.ErrorCode;

public abstract class DomainException extends RuntimeException {

    private final ErrorCode errorCode;

    public DomainException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
