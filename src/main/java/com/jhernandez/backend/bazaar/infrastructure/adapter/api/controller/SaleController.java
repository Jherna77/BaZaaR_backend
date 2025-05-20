package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.service.SaleService;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.ItemDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.SALES;

@RestController
@RequestMapping(SALES)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class SaleController {

    private final SaleService saleService;
    private final ItemDtoMapper mapper;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
    public ResponseEntity<?> getUserSales(@PathVariable Long id) {
        log.info("Finding sales for user shop with ID {}", id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.toDtoList(saleService.getUserSales(id)));
    }

}
