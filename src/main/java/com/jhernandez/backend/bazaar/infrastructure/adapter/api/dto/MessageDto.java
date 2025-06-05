package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {

    private Long id;
    private Long recipientId;
    private String messageDate;
    private String content;
    private Boolean seen;

}
