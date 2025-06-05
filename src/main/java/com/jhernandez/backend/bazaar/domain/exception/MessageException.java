package com.jhernandez.backend.bazaar.domain.exception;

import com.jhernandez.backend.bazaar.domain.model.ErrorCode;

public class MessageException extends DomainException {

    public MessageException(ErrorCode errorCode) {
        super(errorCode);
    }
}
