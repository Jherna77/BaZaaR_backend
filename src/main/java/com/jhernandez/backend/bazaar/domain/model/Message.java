package com.jhernandez.backend.bazaar.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private Long id;
    private User recipient;
    private LocalDateTime messageDate;
    private String content;
    private Boolean seen;

    public Message(User recipient, String content) {
        this.id = null;
        this.recipient = recipient;
        this.content = content;
        this.messageDate = LocalDateTime.now();
        this.seen = false;
    }

}
