package com.jhernandez.backend.bazaar.application.port;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.model.Review;

public interface ReviewRepositoryPort {

    void saveReview(Review review);

    List<Review> findReviewsByProductId(Long productId);

    List<Review> findReviewsByUserId(Long userId);

}
