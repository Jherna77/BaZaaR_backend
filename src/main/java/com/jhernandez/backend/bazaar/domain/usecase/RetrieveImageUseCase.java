package com.jhernandez.backend.bazaar.domain.usecase;

import java.io.IOException;

import com.jhernandez.backend.bazaar.domain.model.ImageFile;

public interface RetrieveImageUseCase {

    ImageFile getImage(String fileName) throws IOException;

}
