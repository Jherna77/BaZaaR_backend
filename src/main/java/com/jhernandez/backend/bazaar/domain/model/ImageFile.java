package com.jhernandez.backend.bazaar.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ImageFile {

    private final byte[] data;
    private final String fileName;
    private final String contentType;

}
