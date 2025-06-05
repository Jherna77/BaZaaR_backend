package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.MessageException;
import com.jhernandez.backend.bazaar.domain.model.Message;

public interface CreateMessageUseCase {

    public void createMessage(Message message) throws MessageException;

}
