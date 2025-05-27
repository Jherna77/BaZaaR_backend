package com.jhernandez.backend.bazaar.domain.exception;

public class BackupException extends DomainException{

        public BackupException(ErrorCode errorCode) {
        super(errorCode);
    }

}
