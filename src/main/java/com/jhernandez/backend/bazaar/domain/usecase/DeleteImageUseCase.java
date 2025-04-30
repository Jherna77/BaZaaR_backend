package com.jhernandez.backend.bazaar.domain.usecase;

import java.io.IOException;
import java.util.List;

public interface DeleteImageUseCase {

    void deleteImageByFilename(String fileName) throws IOException;

    void deleteImageByUrl(String url) throws IOException;

    void deleteImageListByUrl(List<String> urls) throws IOException;

}
