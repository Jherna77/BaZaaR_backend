package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;

public interface DeleteImageUseCase {

    void deleteImageByFilename(String fileName) throws ImageFileException;

    void deleteImageByUrl(String url) throws ImageFileException;

    void deleteImageListByUrl(List<String> urls) throws ImageFileException;

}
