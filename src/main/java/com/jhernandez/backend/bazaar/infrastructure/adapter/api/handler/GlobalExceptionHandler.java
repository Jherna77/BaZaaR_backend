package com.jhernandez.backend.bazaar.infrastructure.adapter.api.handler;

import com.jhernandez.backend.bazaar.domain.exception.UserException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.exception.OrderException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
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
    
}

