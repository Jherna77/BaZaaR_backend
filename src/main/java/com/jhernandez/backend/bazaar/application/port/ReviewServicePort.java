package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.usecase.CreateReviewUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.RetrieveReviewUseCase;

public interface ReviewServicePort extends CreateReviewUseCase, RetrieveReviewUseCase{

}
