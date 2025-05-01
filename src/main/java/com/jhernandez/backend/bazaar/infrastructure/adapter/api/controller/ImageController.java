package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jhernandez.backend.bazaar.application.port.ImageServicePort;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.ImageFileDtoMapper;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(IMAGES)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImageServicePort imageService;
    private final ImageFileDtoMapper imageFileDtoMapper;

    @PostMapping("/upload")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image)
            throws IOException {
        log.info("Uploading image");

        try {
            ImageFile imageFile = imageService.saveImage(imageFileDtoMapper.toDomain(image));
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(imageFileDtoMapper.toDto(imageFile));
        } catch (ImageFileException e) {
            log.error("Error uploading image: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
        }

    }

    @PostMapping("/uploadList")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
    public ResponseEntity<?> uploadImageList(@RequestParam List<MultipartFile> images) {
        log.info("Uploading image list");

        try {
            List<ImageFile> imageFiles = new ArrayList<>();
            for (MultipartFile multipartFile : images) {
                imageFiles.add(imageFileDtoMapper.toDomain(multipartFile));
            }

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(imageService.saveImagesList(imageFiles).stream()
                            .map(imageFileDtoMapper::toDto)
                            .toList());
        } catch (ImageFileException e) {
            log.error("Error uploading image list: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        log.info("Fetching image: {}", filename);
        try {
            ImageFile image = imageService.getImageByFileName(filename);
            ByteArrayResource resource = new ByteArrayResource(image.getData());

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.getContentType()))
                    .body(resource);

        } catch (ImageFileException e) {
            log.error("Error fetching image: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{filename}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
    public ResponseEntity<Void> deleteImage(@PathVariable String filename) {
        log.info("Deleting image: {}", filename);
        try {
            imageService.deleteImageByFilename(filename);
            return ResponseEntity.noContent().build();
        } catch (ImageFileException e) {
            log.error("Error deleting image: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

}