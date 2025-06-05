package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.MessageException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Message;

public interface RetrieveMessageUseCase {

    List<Message> findAllMessages();

    List<Message> findMessagesBySenderId(Long senderId) throws UserException;

    List<Message> findMessagesByReceiverId(Long receiverId) throws UserException;

    Message findMessageById(Long id) throws MessageException;

}
