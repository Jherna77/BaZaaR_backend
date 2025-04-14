package com.jhernandez.backend.bazaar.domain.usecase;

import java.io.IOException;

public interface CreateImageUseCase {

    String saveImage(byte[] image, String fileName) throws IOException;

}
