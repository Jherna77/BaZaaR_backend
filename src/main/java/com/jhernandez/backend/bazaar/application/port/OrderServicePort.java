package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.usecase.CreateOrderUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.RetrieveOrderUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.UpdateOrderUseCase;

public interface OrderServicePort extends
    CreateOrderUseCase, RetrieveOrderUseCase, UpdateOrderUseCase {

}
