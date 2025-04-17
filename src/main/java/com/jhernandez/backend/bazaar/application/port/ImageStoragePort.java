package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.model.ImageFile;

public interface ImageStoragePort {

        String saveImage(ImageFile image);

        ImageFile getImageByFileName(String fileName);

        void deleteImageByFilename(String fileName);

}
