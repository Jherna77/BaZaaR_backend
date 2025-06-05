package com.jhernandez.backend.bazaar.domain.exception;

import com.jhernandez.backend.bazaar.domain.model.ErrorCode;

public class BackupException extends DomainException{

        public BackupException(ErrorCode errorCode) {
        super(errorCode);
    }

}
