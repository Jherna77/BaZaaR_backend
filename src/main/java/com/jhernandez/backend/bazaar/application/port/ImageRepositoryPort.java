package com.jhernandez.backend.bazaar.application.port;

public interface ImageRepositoryPort {

        String save(byte[] image, String fileName);

}
