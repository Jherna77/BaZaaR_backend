package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.MessageException;

public interface UpdateMessageUseCase {

    void setMessageAsSeen(Long messageId) throws MessageException;

}
