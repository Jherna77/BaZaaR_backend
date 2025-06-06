package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.MessageEntity;

public interface JpaMessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findByRecipientId(Long recipientId);

    Boolean existsByRecipientIdAndSeenFalse(Long recipientId);

    // @Modifying
    // @Query("UPDATE MessageEntity m SET m.read = true WHERE m.id = :messageId")
    // void setMessageAsRead(@Param("messageId") Long messageId);

}
