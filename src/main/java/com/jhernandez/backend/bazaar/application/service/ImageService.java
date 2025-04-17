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
    public String saveImage(ImageFile image) throws IOException {
        return imageStoragePort.saveImage(image);
    }

    @Override
    public ImageFile getImageByFileName(String fileName) throws IOException {
        return imageStoragePort.getImageByFileName(fileName);
    }

    @Override
    public void deleteImageByFilename(String fileName) throws IOException {
        imageStoragePort.deleteImageByFilename(fileName);
    }

}
