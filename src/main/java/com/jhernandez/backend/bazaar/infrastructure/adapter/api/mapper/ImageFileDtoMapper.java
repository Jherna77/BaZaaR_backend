package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jhernandez.backend.bazaar.domain.model.ImageFile;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ImageFileDto;

@Component
public class ImageFileDtoMapper {

    public ImageFileDto toDto(ImageFile imageFile) {
        return new ImageFileDto(
            imageFile.getImageUrl()
        );
    }

    public ImageFile toDomain(MultipartFile multipartFile) {
        try {
            return multipartFile != null
                ? new ImageFile(
                    multipartFile.getBytes(),
                    multipartFile.getOriginalFilename(),
                    multipartFile.getContentType(),
                    null)
                : null;
        } catch (IOException e) {
            return null;
        }
    }

    public List<ImageFile> toDomainList(List<MultipartFile> multipartFileList) {
        return multipartFileList != null
            ? multipartFileList.stream()
                .map(this::toDomain)
                .toList()
            : null;
    }

    public List<ImageFileDto> toDtoList(List<ImageFile> imageFileList) {
        return imageFileList.stream()
            .map(this::toDto)
            .toList();
    }
}
