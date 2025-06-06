package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.MessageException;
import com.jhernandez.backend.bazaar.domain.model.Message;

public interface DeleteMessageUseCase {

    List<Message> deleteMessageById(Long id) throws MessageException;

}
