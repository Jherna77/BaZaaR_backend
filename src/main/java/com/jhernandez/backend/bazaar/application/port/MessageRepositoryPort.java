package com.jhernandez.backend.bazaar.application.port;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.model.Message;

public interface MessageRepositoryPort {

    void saveMessage(Message message);

    List<Message> findAllMessages();

    List<Message> findMessagesByRecipientId(Long receiverId);

    Optional<Message> findMessageById(Long id);

    Boolean hasNewMessages(Long recipientId);

    void deleteMessageById(Long id);
}
