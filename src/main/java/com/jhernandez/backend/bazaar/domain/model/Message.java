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
    private User sender;
    private User receiver;
    private LocalDateTime maessageDate;
    private String content;
    private Boolean read;

}
