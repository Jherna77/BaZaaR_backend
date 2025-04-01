package com.jhernandez.backend.bazaar.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.dto.CategoryDto;
import com.jhernandez.backend.bazaar.entities.CategoryEntity;
import com.jhernandez.backend.bazaar.mappers.CategoryMapper;
import com.jhernandez.backend.bazaar.repositories.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    // @Transactional(readOnly = true)
    // @Override
    // public Optional<CategoryEntity> findById(Long id) {
    // return categoryRepository.findById(id);
    // }

    @Transactional
    private CategoryEntity saveEntity(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    @Override
    public CategoryDto save(CategoryEntity category) {
        return categoryMapper.toDto(saveEntity(category));
    }

    @Override
    @Transactional
    public Optional<CategoryDto> update(Long id, CategoryEntity category) {
        return categoryRepository.findById(id).map(cat -> {
            cat.setName(category.getName());
            cat.setEnabled(category.isEnabled());
            return save(cat);
        });
    }

    @Override
    @Transactional
    public Optional<CategoryDto> disable(Long id) {
        return categoryRepository.findById(id).map(cat -> {
            cat.setEnabled(false);
            return save(cat);
        });
    }
}
