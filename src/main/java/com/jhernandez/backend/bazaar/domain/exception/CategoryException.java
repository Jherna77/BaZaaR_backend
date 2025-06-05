package com.jhernandez.backend.bazaar.domain.exception;

import com.jhernandez.backend.bazaar.domain.model.ErrorCode;

public class CategoryException extends DomainException {

    public CategoryException(ErrorCode errorCode) {
        super(errorCode);
    }

}
