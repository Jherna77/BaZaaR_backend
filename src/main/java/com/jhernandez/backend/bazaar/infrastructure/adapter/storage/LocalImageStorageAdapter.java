package com.jhernandez.backend.bazaar.infrastructure.adapter.storage;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.IMG_CONTENT_TYPE_OCTET;
import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.UPLOAD_DIR;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public String saveImage(ImageFile image) {
        String fileName = UUID.randomUUID() + "-" + image.getFileName();
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, image.getData());
            return "/api/images/" + fileName;
        } catch (IOException e) {
            log.error("Error saving image: {}", e.getMessage());
            throw new ImageStorageException();
        }
    }

    // @Override
    // public String saveImage(byte[] image, String originalFileName) {
    //     String fileName = UUID.randomUUID() + "-" + originalFileName;
    //     Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
    //     try {
    //         Files.createDirectories(filePath.getParent());
    //         Files.write(filePath, image);
    //         return "/api/images/" + fileName;
    //     } catch (IOException e) {
    //         log.error("Error saving image: {}", e.getMessage());
    //         throw new ImageStorageException();
    //     }
    // }

    @Override
    public ImageFile getImageByFileName(String fileName) {
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();

        if (!Files.exists(filePath)) {
            log.error("Image not found: {}", fileName);
            throw new ImageStorageException();
        }

        if (!Files.isReadable(filePath)) {
            log.error("Image not readable: {}", fileName);
            throw new ImageStorageException();
        }
        
        // byte[] data;
        try {
            // data = Files.readAllBytes(filePath);
            String contentType = Files.probeContentType(filePath);
            return new ImageFile(
                Files.readAllBytes(filePath),
                fileName,
                contentType != null ? contentType : IMG_CONTENT_TYPE_OCTET);
        } catch (IOException e) {
            log.error("Error reading image: {}", e.getMessage());
            throw new ImageStorageException();
        }
        
    }

    @Override
    public void deleteImageByFilename(String fileName) {
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            log.error("Error deleting image: {}", e.getMessage());
            throw new ImageStorageException();
        }
    }

}
