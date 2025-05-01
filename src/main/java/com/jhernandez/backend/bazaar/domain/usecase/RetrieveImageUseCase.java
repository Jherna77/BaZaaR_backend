package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;

public interface RetrieveImageUseCase {

    ImageFile getImageByFileName(String fileName) throws ImageFileException;

}
