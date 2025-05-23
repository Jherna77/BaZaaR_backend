package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Review;

public interface RetrieveReviewUseCase {

    List<Review> findReviewsByProductId(Long productId) throws ProductException;

    List<Review> findReviewsByUserId(Long userId) throws UserException;

}
