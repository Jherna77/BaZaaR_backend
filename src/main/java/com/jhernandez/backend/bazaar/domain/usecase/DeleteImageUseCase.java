package com.jhernandez.backend.bazaar.domain.usecase;

import java.io.IOException;

public interface DeleteImageUseCase {

    void deleteImageByFilename(String fileName) throws IOException;

    void deleteImageByUrl(String url) throws IOException;

}
