package com.jhernandez.backend.bazaar.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDto {
    private boolean enabled;
    private String name;
    List<String> products;
}
