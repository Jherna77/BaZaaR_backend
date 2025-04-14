package com.jhernandez.backend.bazaar.application.service;

import java.io.IOException;

import com.jhernandez.backend.bazaar.application.port.ImageStoragePort;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;
import com.jhernandez.backend.bazaar.application.port.ImageServicePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImageService implements ImageServicePort {

    private final ImageStoragePort imageStoragePort;

    @Override
    public String saveImage(byte[] image, String fileName) throws IOException {
        return imageStoragePort.saveImage(image, fileName);
    }

    @Override
    public ImageFile getImage(String fileName) throws IOException {
        return imageStoragePort.getImage(fileName);
    }

}
