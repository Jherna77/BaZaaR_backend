package com.jhernandez.backend.bazaar.application.port;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.model.ImageFile;

public interface ImageStoragePort {

        ImageFile saveImage(ImageFile image);

        List<ImageFile> saveImagesList(List<ImageFile> images);

        ImageFile getImageByFileName(String fileName);

        void deleteImageByFilename(String fileName);

        void deleteImageByUrl(String url);


}
