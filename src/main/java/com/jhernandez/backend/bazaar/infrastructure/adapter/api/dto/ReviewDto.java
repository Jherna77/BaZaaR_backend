package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private Long id;
    private Long productId;
    private Long userId;
    private String comment;
    private Integer rating;
    private LocalDateTime reviewDate;

}
