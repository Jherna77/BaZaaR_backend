package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.usecase.RetrieveCartUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.UpdateCartUseCase;

public interface CartServicePort extends RetrieveCartUseCase, UpdateCartUseCase {

    // This interface combines the functionalities of both UpdateCartUseCase and RetrieveCartUseCase
    // No additional methods are needed here as it inherits from both interfaces

}
