package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ProductEntity;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaProductRepository;

@SpringBootTest
class ProductEntityMapperTest {

    @Autowired
    private ProductEntityMapper productMapper;

    @Autowired
    private JpaProductRepository productRepository;

    @Transactional(readOnly = true)
    @Test
    void testMapProductEntityToDomain() {
        Optional<ProductEntity> entityOpt = productRepository.findById(1L);
        assertTrue(entityOpt.isPresent());

        ProductEntity entity = entityOpt.get();
        Product domain = productMapper.toDomain(entity);

        assertNotNull(domain);
        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getName(), domain.getName());
        assertEquals(entity.getShop().getId(), domain.getShop().getId());
        assertNull(domain.getShop().getShopProducts());
    }

    @Transactional(readOnly = true)
    @Test
    void testMapProductEntityToEntity() {
        ProductEntity entity = productRepository.findById(1L).orElseThrow();
        Product domain = productMapper.toDomain(entity);
        domain.setId(77L);
        ProductEntity mappedEntity = productMapper.toEntity(domain);

        assertNotNull(mappedEntity);
        assertEquals(domain.getId(), mappedEntity.getId());
        assertEquals(domain.getName(), mappedEntity.getName());
        assertEquals(domain.getShop().getId(), mappedEntity.getShop().getId());
        assertNull(mappedEntity.getShop().getShopProducts());

    }
}

// @DataJpaTest
// public class ProductEntityMapperTest {

// private final ProductEntityMapper mapper = new ProductEntityMapperImpl(new
// CategoryEntityMapperImpl(),
// new UserEntityMapperImpl(null, null, null, null));

// @Autowired
// private JpaProductRepository productRepository;

// @Test
// void shouldMapProductEntityToDomainWithoutInfiniteLoop() {
// final Optional<ProductEntity> productEntity = productRepository.findById(1L);

// Product product = mapper.toDomain(productEntity.get());

// assertNotNull(product);
// assertEquals(productEntity.get().getId(), product.getId());
// assertEquals(productEntity.get().getName(), product.getName());
// assertEquals(productEntity.get().getOwner().getId(),
// product.getOwner().getId());
// assertNull(product.getOwner().getShop());
// }
// }
