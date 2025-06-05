package com.jhernandez.backend.bazaar.application.service;

import java.util.List;

import com.jhernandez.backend.bazaar.application.port.MessageRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.MessageServicePort;
import com.jhernandez.backend.bazaar.domain.exception.ErrorCode;
import com.jhernandez.backend.bazaar.domain.exception.MessageException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Message;

public class MessageService implements MessageServicePort {

    private final MessageRepositoryPort messageRepository;

    public MessageService(MessageRepositoryPort messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void createMessage(Message message) throws MessageException {
        // if (message == null) {
        //     throw new MessageException(ErrorCode.MESSAGE_NOT_NULL);
        // }
        // if (message.getSender() == null || message.getReceiver() == null) {
        //     throw new MessageException(ErrorCode.SENDER_AND_RECEIVER_NOT_NULL);
        // }
        messageRepository.saveMessage(message);
    }

    @Override
    public List<Message> findAllMessages() {
        return messageRepository.findAllMessages();
    }

    @Override
    public List<Message> findMessagesBySenderId(Long senderId) throws UserException {
        if (senderId == null) {
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        }
        return messageRepository.findMessagesBySenderId(senderId);
    }

    @Override
    public List<Message> findMessagesByReceiverId(Long receiverId) throws UserException {
        if (receiverId == null) {
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        }
        return messageRepository.findMessagesByReceiverId(receiverId);
    }

    @Override
    public Message findMessageById(Long id) throws MessageException {
        if (id == null) {
            throw new MessageException(ErrorCode.MESSAGE_ID_NOT_NULL);
        }
        return messageRepository.findMessageById(id)
                .orElseThrow(() -> new MessageException(ErrorCode.MESSAGE_NOT_FOUND));
    }

    @Override
    public void setMessageAsRead(Long messageId) throws MessageException {
        if (messageId == null) {
            throw new MessageException(ErrorCode.MESSAGE_ID_NOT_NULL);
        }
        Message message = findMessageById(messageId);
        message.setRead(true);
        messageRepository.saveMessage(message);
    }

    @Override
    public void deleteMessageById(Long id) throws MessageException {
        if (id == null) {
            throw new MessageException(ErrorCode.MESSAGE_ID_NOT_NULL);
        }
        if (!messageRepository.findMessageById(id).isPresent()) {
            throw new MessageException(ErrorCode.MESSAGE_NOT_FOUND);
        }
        messageRepository.deleteMessageById(id);
    }

}
