package com.jhernandez.backend.bazaar.infrastructure.adapter.storage;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.UPLOAD_DIR;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.application.port.ImageRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.ImageStorageException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LocalImageStorageAdapter implements ImageRepositoryPort {

    @Override
    public String save(byte[] image, String originalFileName) {
        String fileName = UUID.randomUUID() + "-" + originalFileName;
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, image);
        } catch (IOException e) {
            log.error("Error saving image: {}", e.getMessage());
            throw new ImageStorageException();
        }
        return "/api/images/" + fileName;
    }

}
