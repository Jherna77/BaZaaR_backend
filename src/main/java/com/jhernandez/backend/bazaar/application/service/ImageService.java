package com.jhernandez.backend.bazaar.application.service;

import java.io.IOException;

import com.jhernandez.backend.bazaar.application.port.ImageRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.ImageServicePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImageService implements ImageServicePort {

    private final ImageRepositoryPort imageRepositoryPort;

    @Override
    public String save(byte[] image, String fileName) throws IOException {
        return imageRepositoryPort.save(image, fileName);
    }

}
