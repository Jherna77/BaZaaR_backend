package com.jhernandez.backend.bazaar.domain.exception;

public abstract class DomainException extends Exception {

    private final ErrorCode errorCode;

    public DomainException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
