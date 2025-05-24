package com.jhernandez.backend.bazaar.application.service;

import java.util.List;

import com.jhernandez.backend.bazaar.application.port.ProductRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.ReviewRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.ReviewServicePort;
import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.ErrorCode;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.ReviewException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.domain.model.Review;
import com.jhernandez.backend.bazaar.domain.model.User;

public class ReviewService implements ReviewServicePort {

    private final ReviewRepositoryPort reviewRepository;
    private final UserRepositoryPort userRepository;
    private final ProductRepositoryPort productRepository;

    public ReviewService(ReviewRepositoryPort reviewRepository, UserRepositoryPort userRepository,
            ProductRepositoryPort productRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void createReview(Review review) throws UserException, ProductException {
        Product product = productRepository.findProductById(review.getProduct().getId())
                .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));
        User user = userRepository.findUserById(review.getUser().getId())
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        review.setProduct(product);
        review.setUser(user);
        review.setReviewDateNow();
        reviewRepository.saveReview(review);
    }

    @Override
    public Review findReviewById(Long reviewId) throws ReviewException {
        if (reviewId == null) {
            throw new ReviewException(ErrorCode.REVIEW_ID_NOT_NULL);
        }
        return reviewRepository.findReviewById(reviewId)
                .orElseThrow(() -> new ReviewException(ErrorCode.REVIEW_NOT_FOUND));
    }


    @Override
    public List<Review> findReviewsByProductId(Long productId) throws ProductException {
        if (productId == null) {
            throw new UserException(ErrorCode.PRODUCT_ID_NOT_NULL);
        }
        return reviewRepository.findReviewsByProductId(productId);
    }

    @Override
    public List<Review> findReviewsByUserId(Long userId) throws UserException {
        if (userId == null) {
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        }
        return reviewRepository.findReviewsByUserId(userId);
    }

}
