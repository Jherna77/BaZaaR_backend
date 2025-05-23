package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.application.port.ReviewRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.Review;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.ReviewEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlReviewRepositoryAdapter implements ReviewRepositoryPort {

    private final JpaReviewRepository reviewRepository;
    private final ReviewEntityMapper reviewEntityMapper;

    @Override
    public void saveReview(Review review) {
        log.info("Saving review...");
        reviewRepository.save(reviewEntityMapper.toEntity(review));
    }

    @Override
    public List<Review> findReviewsByProductId(Long productId) {
        log.info("Finding reviews for product with ID {}", productId);
        return reviewRepository.findByProductId(productId).stream()
                .map(reviewEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Review> findReviewsByUserId(Long userId) {
        log.info("Finding reviews for user with ID {}", userId);
        return reviewRepository.findByUserId(userId).stream()
                .map(reviewEntityMapper::toDomain)
                .toList();
    }


}
