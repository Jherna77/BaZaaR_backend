package com.jhernandez.backend.bazaar.domain.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Category {
    private Long id;
    private String name;
    private List<Product> products;
}
