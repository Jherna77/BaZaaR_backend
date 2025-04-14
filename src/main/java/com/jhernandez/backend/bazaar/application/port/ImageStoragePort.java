package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.model.ImageFile;

public interface ImageStoragePort {

        String saveImage(byte[] image, String fileName);

        ImageFile getImage(String fileName);

}
