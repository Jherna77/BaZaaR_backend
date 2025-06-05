package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.MessageException;

public interface DeleteMessageUseCase {

    void deleteMessageById(Long id) throws MessageException;

}
