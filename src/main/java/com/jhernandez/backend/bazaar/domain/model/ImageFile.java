package com.jhernandez.backend.bazaar.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageFile {

    private byte[] data;
    private String fileName;
    private String contentType;
    private String imageUrl;

}
