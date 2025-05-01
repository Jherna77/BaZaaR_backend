package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;

public interface CreateImageUseCase {

    ImageFile saveImage(ImageFile image) throws ImageFileException;

    List<ImageFile> saveImagesList(List<ImageFile> images) throws ImageFileException;

}
