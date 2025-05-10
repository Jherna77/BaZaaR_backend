package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.application.port.CartItemRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.CartItem;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.CartItemEntity;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.CartItemEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaCartItemRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlCartItemRepository implements CartItemRepositoryPort{

    private final JpaCartItemRepository repository;
    private final CartItemEntityMapper mapper;

    @Transactional
    @Override
    public Optional<CartItem> saveCartItem(CartItem cartItem) {
        log.info("Saving cartItem {}", cartItem.toString());
        CartItemEntity cartItemEntity = mapper.toEntity(cartItem);
        return Optional.of(mapper.toDomain(
                repository.save(cartItemEntity)));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<CartItem> findCartItemById(Long id) {
        log.info("Finding cartItem with ID {}", id);
        return repository.findById(id)
                .map(mapper::toDomain);
    }

}
