package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.jhernandez.backend.bazaar.domain.model.ImageFile;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ImageFileDto;

public class ImageFileDtoMapper {

    public ImageFileDto toDto(ImageFile imageFile) {
        return new ImageFileDto(
            imageFile.getImageUrl()
        );
    }

    public ImageFile toDomain(MultipartFile multipartFile) {
        try {
            return new ImageFile(
                multipartFile.getBytes(),
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                null);
        } catch (IOException e) {
            return null;
        }
    }
}
