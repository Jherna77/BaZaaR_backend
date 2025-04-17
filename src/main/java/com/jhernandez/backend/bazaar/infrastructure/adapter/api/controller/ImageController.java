package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
// import org.springframework.core.io.UrlResource;
// import org.springframework.http.HttpStatus;
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
import com.jhernandez.backend.bazaar.domain.model.ImageFile;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ImageUploadResponseDto;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.*;

import java.io.IOException;
// import java.net.MalformedURLException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.nio.file.StandardCopyOption;
// import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(IMAGES)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImageServicePort imageService;

    @PostMapping("/upload")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
    public ResponseEntity<ImageUploadResponseDto> uploadImage(@RequestParam("image") MultipartFile image)
            throws IOException {
        ImageFile imageFile = new ImageFile(image.getBytes(), image.getOriginalFilename(), image.getContentType());
        return ResponseEntity.ok(new ImageUploadResponseDto(
                imageService.saveImage(imageFile)));

        // return ResponseEntity.ok(new ImageUploadResponseDto(
        // imageService.saveImage(file.getBytes(),
        // file.getOriginalFilename())));
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            ImageFile image = imageService.getImageByFileName(filename);
            ByteArrayResource resource = new ByteArrayResource(image.getData());

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.getContentType()))
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{filename}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
    public ResponseEntity<Void> deleteImage(@PathVariable String filename) {
        try {
            imageService.deleteImageByFilename(filename);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

// @PostMapping("/upload")
// public ResponseEntity<String> uploadImage(@RequestParam("image")
// MultipartFile file) throws IOException {
// String url = uploadImageUseCase.upload(file.getBytes(),
// file.getOriginalFilename());
// return ResponseEntity.ok(url);
// }

// @PostMapping("/upload")
// @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
// public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile
// file) {
// try {
// String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
// Path filePath = Paths.get(UPLOAD_DIR, fileName);

// Files.createDirectories(filePath.getParent());
// Files.copy(file.getInputStream(), filePath,
// StandardCopyOption.REPLACE_EXISTING);

// String imageUrl = "/api/images/" + fileName;

// return ResponseEntity.ok(new ImageUploadResponseDto(imageUrl));
// } catch (IOException e) {
// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload
// failed");
// }
// }

// @GetMapping("/{filename:.+}")
// public ResponseEntity<Resource> getImage(@PathVariable String filename) {
// try {
// Path filePath = Paths.get(UPLOAD_DIR).resolve(filename).normalize();
// Resource resource = new UrlResource(filePath.toUri());

// if (resource.exists()) {
// return ResponseEntity.ok()
// .contentType(MediaType.IMAGE_JPEG)
// .body(resource);
// } else {
// return ResponseEntity.notFound().build();
// }
// } catch (MalformedURLException e) {
// return ResponseEntity.badRequest().build();
// }
// }
