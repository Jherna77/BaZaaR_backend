package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.usecase.CreateMessageUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.DeleteMessageUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.RetrieveMessageUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.UpdateMessageUseCase;

public interface MessageServicePort extends CreateMessageUseCase, RetrieveMessageUseCase, 
    UpdateMessageUseCase, DeleteMessageUseCase {
    
}
