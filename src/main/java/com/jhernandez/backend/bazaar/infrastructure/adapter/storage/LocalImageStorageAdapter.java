package com.jhernandez.backend.bazaar.infrastructure.adapter.storage;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.IMG_CONTENT_TYPE_OCTET;
import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.UPLOAD_DIR;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.application.port.ImageStoragePort;
import com.jhernandez.backend.bazaar.domain.exception.ImageStorageException;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LocalImageStorageAdapter implements ImageStoragePort {

    @Override
    public ImageFile saveImage(ImageFile image) {
        String fileName = UUID.randomUUID() + "-" + image.getFileName();
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
        log.info("Saving image {}", filePath.toString());
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, image.getData());
            image.setImageUrl("/api/images/" + fileName);
            return image;
        } catch (IOException e) {
            log.error("Error saving image: {}", e.getMessage());
            throw new ImageStorageException();
        }
    }

    @Override
    public List<ImageFile> saveImagesList(List<ImageFile> images) {
        return images.stream()
            .map(this::saveImage)
            .toList();
    }

    @Override
    public ImageFile getImageByFileName(String fileName) {
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();

        log.info("Fetching image {}", filePath.toString());

        if (!Files.exists(filePath)) {
            log.error("Image not found: {}", fileName);
            throw new ImageStorageException();
        }

        if (!Files.isReadable(filePath)) {
            log.error("Image not readable: {}", fileName);
            throw new ImageStorageException();
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
            throw new ImageStorageException();
        }
    }

    @Override
    public void deleteImageByFilename(String fileName) {
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();

        log.info("Deleting image {}", filePath.toString());
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            log.error("Error deleting image: {}", e.getMessage());
            throw new ImageStorageException();
        }
    }

    @Override
    public void deleteImageByUrl(String url) {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();

        log.info("Deleting image {}", filePath.toString());
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            log.error("Error deleting image: {}", e.getMessage());
            throw new ImageStorageException();
        }
    }

}
