package com.jhernandez.backend.bazaar.application.service;

import java.util.ArrayList;
import java.util.List;

import com.jhernandez.backend.bazaar.application.port.ImageStoragePort;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;
import com.jhernandez.backend.bazaar.application.port.ImageServicePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImageService implements ImageServicePort {

    private final ImageStoragePort imageStoragePort;

    @Override
    public ImageFile saveImage(ImageFile image) throws ImageFileException {
        if (image == null) throw new ImageFileException("Image is null");
        return imageStoragePort.saveImage(image);
    }

    @Override
    public List<ImageFile> saveImagesList(List<ImageFile> images) throws ImageFileException {
        if (images == null || images.isEmpty())
            throw new ImageFileException("Image list is empty");
        List<ImageFile> savedImages = new ArrayList<>();
        for (ImageFile image : images) {
            savedImages.add(imageStoragePort.saveImage(image));
        }
        return savedImages;        
    }

    @Override
    public ImageFile getImageByFileName(String fileName) throws ImageFileException {
        if (fileName == null || fileName.isEmpty()) throw new ImageFileException("Image filename is empty");
        return imageStoragePort.getImageByFileName(fileName);
    }

    @Override
    public void deleteImageByFilename(String fileName) throws ImageFileException {
        if (fileName == null || fileName.isEmpty()) throw new ImageFileException("Image filename is empty");
        imageStoragePort.deleteImageByFilename(fileName);
    }

    @Override
    public void deleteImageByUrl(String url) throws ImageFileException {
        if (url == null || url.isEmpty()) throw new ImageFileException("Image URL is empty");
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        deleteImageByFilename(fileName);
    }

    @Override
    public void deleteImageListByUrl(List<String> urls) throws ImageFileException {
        if (urls == null || urls.isEmpty()) throw new ImageFileException("Image URL list is empty");
        for (String url : urls) {
            deleteImageByFilename(url.substring(url.lastIndexOf("/") + 1));
        }
    }

}
