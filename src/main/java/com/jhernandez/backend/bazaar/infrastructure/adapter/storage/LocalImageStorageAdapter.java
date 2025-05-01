package com.jhernandez.backend.bazaar.infrastructure.adapter.storage;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.IMAGES;
import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.IMG_CONTENT_TYPE_OCTET;
import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.UPLOAD_DIR;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.application.port.ImageStoragePort;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LocalImageStorageAdapter implements ImageStoragePort {

    @Override
    public ImageFile saveImage(ImageFile image) throws ImageFileException {
        String fileName = image.getFileName();
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
        log.info("Saving image {}", filePath.toString());
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, image.getData());
            image.setImageUrl(IMAGES + "/" + fileName);
            return image;
        } catch (IOException e) {
            log.error("Error saving image: {}", e.getMessage());
            throw new ImageFileException("Error saving image: ");
        }
    }

    @Override
    public ImageFile getImageByFileName(String fileName) throws ImageFileException {
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();

        log.info("Fetching image {}", filePath.toString());

        if (!Files.exists(filePath)) {
            log.error("Image not found: {}", fileName);
            throw new ImageFileException("Image not found");
        }

        if (!Files.isReadable(filePath)) {
            log.error("Image not readable: {}", fileName);
            throw new ImageFileException("Image not readable");
        }
        
        try {
            String contentType = Files.probeContentType(filePath);
            return new ImageFile(
                Files.readAllBytes(filePath),
                fileName,
                contentType != null ? contentType : IMG_CONTENT_TYPE_OCTET
                ,null);
        } catch (IOException e) {
            log.error("Error reading image: {}", e.getMessage());
            throw new ImageFileException("Error reading image");
        }
    }

    @Override
    public void deleteImageByFilename(String fileName) throws ImageFileException {
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();

        log.info("Deleting image {}", filePath.toString());
        
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            log.error("Error deleting image: {}", e.getMessage());
            throw new ImageFileException("Error deleting image");
        }
    }

}
