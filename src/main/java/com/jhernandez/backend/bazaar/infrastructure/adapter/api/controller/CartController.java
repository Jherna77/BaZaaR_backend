package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.port.CartServicePort;
import com.jhernandez.backend.bazaar.domain.exception.DomainException;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.CartItemDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.CartItemDtoMapper;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.CART;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CART)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartServicePort cartService;
    private final CartItemDtoMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserCart(@PathVariable Long id) {
        log.info("Finding cart for user ID {}", id);
        try {
            return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.toDtoList(cartService.getUserCart(id)));
        } catch (DomainException e) {
            log.error("Error getting cart with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addItemToCart(@PathVariable Long id, @RequestBody CartItemDto item) {
        log.info("Adding item {} to cart for the user with ID {}", item.getProduct().getName(), id);
        try {
            cartService.addItemToCart(id, mapper.toDomain(item));
            return ResponseEntity.noContent().build();
        } catch (DomainException e) {
            log.error("Error adding item to cart for user with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
