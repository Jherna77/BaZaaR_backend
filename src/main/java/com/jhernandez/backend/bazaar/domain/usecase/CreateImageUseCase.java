package com.jhernandez.backend.bazaar.domain.usecase;

import java.io.IOException;
import java.util.List;

import com.jhernandez.backend.bazaar.domain.model.ImageFile;

public interface CreateImageUseCase {

    ImageFile saveImage(ImageFile image) throws IOException;

    List<ImageFile> saveImagesList(List<ImageFile> images) throws IOException;

}
