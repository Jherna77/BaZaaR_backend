package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.application.port.CategoryRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.CategoryEntity;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.CategoryEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaCategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlCategoryRepositoryAdapter implements CategoryRepositoryPort {

    private final JpaCategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Transactional
    @Override
    public Optional<Category> createCategory(Category category) {
        log.info("Creating category {}", category.getName());
        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);
        return Optional.of(categoryEntityMapper.toDomain(
                categoryRepository.save(categoryEntity)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> findAllCategories() {
        log.info("Finding all categories {}");
        return categoryRepository.findAll().stream()
                .map(categoryEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    @Override
    public Optional<Category> findCategoryById(Long id) {
        log.info("Finding category with ID {}", id);
        return categoryRepository.findById(id).map(categoryEntityMapper::toDomain);
    }

    @Transactional
    @Override
    public Optional<Category> updateCategory(Category category) {
        log.info("Updating category", category.getName());
        return categoryRepository.findById(category.getId()).map(existingCategory -> {
            existingCategory.setName(category.getName());
            existingCategory.setImageUrl(category.getImageUrl());
            return categoryEntityMapper.toDomain(categoryRepository.save(existingCategory));
        });
    }

    @Transactional
    @Override
    public Optional<Category> enableCategoryById(Long id) {
        log.info("Enabling category with ID {}", id);
        return categoryRepository.findById(id).map(category -> {
            category.setEnabled(true);
            return categoryEntityMapper.toDomain(categoryRepository.save(category));
        });
    }

    @Transactional
    @Override
    public Optional<Category> disableCategoryById(Long id) {
        log.info("Disabling category with ID {}", id);
        return categoryRepository.findById(id).map(category -> {
            category.setEnabled(false);
            return categoryEntityMapper.toDomain(categoryRepository.save(category));
        });
    }

    @Transactional
    @Override
    public void deleteCategoryById(Long id) {
        log.info("Deleting category with ID {}", id);
        categoryRepository.deleteById(id);
    }

}
