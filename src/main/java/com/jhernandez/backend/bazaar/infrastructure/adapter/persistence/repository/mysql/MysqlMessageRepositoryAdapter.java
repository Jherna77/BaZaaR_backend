package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.application.port.MessageRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.Message;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.MessageEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaMessageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlMessageRepositoryAdapter implements MessageRepositoryPort {

    private final JpaMessageRepository messageRepository;
    private final MessageEntityMapper messageEntityMapper;

    @Override
    public void saveMessage(Message message) {
        log.info("Saving message from {} to {}", message.getSender().getName(), message.getReceiver().getName());
        messageRepository.save(messageEntityMapper.toEntity(message));
    }

    @Override
    public List<Message> findAllMessages() {
        log.info("Finding all messages");
        return messageRepository.findAll().stream()
                .map(messageEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Message> findMessagesBySenderId(Long senderId) {
        log.info("Finding messages sent by user with ID {}", senderId);
        return messageRepository.findBySenderId(senderId).stream()
                .map(messageEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Message> findMessagesByReceiverId(Long receiverId) {
        log.info("Finding messages received by user with ID {}", receiverId);
        return messageRepository.findByReceiverId(receiverId).stream()
                .map(messageEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Message> findMessageById(Long id) {
        log.info("Finding message with ID {}", id);
        return messageRepository.findById(id).map(messageEntityMapper::toDomain);
    }

    @Override
    public void deleteMessageById(Long id) {
        log.info("Deleting message with ID {}", id);
        messageRepository.deleteById(id);
    }

}
