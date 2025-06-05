package com.jhernandez.backend.bazaar.domain.exception;

import com.jhernandez.backend.bazaar.domain.model.ErrorCode;

public class UserException extends DomainException {

    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }

}
