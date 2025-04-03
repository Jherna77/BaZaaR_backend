package com.jhernandez.backend.bazaar.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.domain.models.Category;
import com.jhernandez.backend.bazaar.domain.ports.out.CategoryRepositoryPort;
import com.jhernandez.backend.bazaar.infrastructure.entities.CategoryEntity;
import com.jhernandez.backend.bazaar.infrastructure.mappers.CategoryMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JpaCategoryRepositoryAdapter implements CategoryRepositoryPort {

    @Autowired
    private JpaCategoryRepository categoryRepository;
    
    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional
    @Override
    public Optional<Category> createCategory(Category category) {
        log.info("Creating category {}", category.getName());
        CategoryEntity categoryEntity = categoryMapper.toEntity(category);
        return Optional.of(categoryMapper.toDomain(
                categoryRepository.save(categoryEntity)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getAllCategories() {
        log.info("Getting all categories {}");
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    @Override
    public Optional<Category> getCategoryById(Long id) {
        log.info("Getting category with ID {}", id);
        return categoryRepository.findById(id).map(categoryMapper::toDomain);
    }

    @Transactional
    @Override
    public Optional<Category> updateCategory(Long id, Category category) {
        log.info("Updating category with ID {}", id);
        return categoryRepository.findById(id).map(existingCategory -> {
            existingCategory.setName(category.getName());
            // existingCategory.setEnabled(category.isEnabled());
            return categoryMapper.toDomain(categoryRepository.save(existingCategory));
        });
    }

    @Transactional
    @Override
    public void deleteCategoryById(Long id) {
        log.info("Deleting category with ID {}", id);
        categoryRepository.deleteById(id);
    }

}
