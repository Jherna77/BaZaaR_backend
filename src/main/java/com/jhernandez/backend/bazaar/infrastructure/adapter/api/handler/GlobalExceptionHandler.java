package com.jhernandez.backend.bazaar.infrastructure.adapter.api.handler;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.stripe.exception.StripeException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.DomainException;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.exception.OrderException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.ReviewException;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    /**
     * Handles UserException and returns a localized error message.
     *
     * @param ex     the UserException thrown
     * @param locale the locale for localization
     * @return a ResponseEntity with the error message
     */

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<String> handleDomainException(DomainException ex, Locale locale) {
        String message = messageSource.getMessage(ex.getErrorCode().getCode(), null, locale);
        log.error("DomainException: {}", message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleUserException(UserException ex, Locale locale) {
        String message = messageSource.getMessage(ex.getErrorCode().getCode(), null, locale);
        log.error("UserException: {}", message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<String> handleCategoryException(CategoryException ex, Locale locale) {
        String message = messageSource.getMessage(ex.getErrorCode().getCode(), null, locale);
        log.error("CategoryException: {}", message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<String> handleProductException(ProductException ex, Locale locale) {
        String message = messageSource.getMessage(ex.getErrorCode().getCode(), null, locale);
        log.error("ProductException: {}", message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(ImageFileException.class)
    public ResponseEntity<String> handleImageFileException(ImageFileException ex, Locale locale) {
        String message = messageSource.getMessage(ex.getErrorCode().getCode(), null, locale);
        log.error("ImageFileException: {}", message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<String> handleOrderException(OrderException ex, Locale locale) {
        String message = messageSource.getMessage(ex.getErrorCode().getCode(), null, locale);
        log.error("OrderException: {}", message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(ReviewException.class)
    public ResponseEntity<String> handleReviewException(ReviewException ex, Locale locale) {
        String message = messageSource.getMessage(ex.getErrorCode().getCode(), null, locale);
        log.error("ReviewException: {}", message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(StripeException.class)
    public ResponseEntity<String> handleStripeException(StripeException ex, Locale locale) {
        String message = messageSource.getMessage(ex.getCode(), null, locale);
        log.error("StripeException: {}", message);
        return ResponseEntity.badRequest().body(message);
    }
    
}

