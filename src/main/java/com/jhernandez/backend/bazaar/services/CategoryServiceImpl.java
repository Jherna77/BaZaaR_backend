package com.jhernandez.backend.bazaar.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.dto.CategoryDto;
import com.jhernandez.backend.bazaar.entities.CategoryEntity;
import com.jhernandez.backend.bazaar.entities.ProductEntity;
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
            return save(cat);
        });
    }

    // @Override
    // @Transactional
    // public Optional<CategoryDto> update(Long id, CategoryEntity category) {
    // Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);
    // if (categoryOptional.isPresent()) {
    // CategoryEntity categoryDb = categoryOptional.orElseThrow();

    // categoryDb.setName(category.getName());
    // return Optional.of(save(categoryDb));
    // }
    // return Optional.empty();
    // }

    // @Transactional
    // @Override
    // public CategoryDto disableById(Long id) {
    //     Optional<CategoryEntity> category = categoryRepository.findById(id);
    //     category.ifPresent(cat -> {
    //     cat.setEnabled(false); // Deshabilitar la categoría
    //     categoryRepository.save(cat); // Guardar la categoría deshabilitada
    //     });
    //     return null; // Devolver null o lanzar una excepción si no se encuentra la categoría
    // }

    @Override
    @Transactional
    public Optional<CategoryDto> disable(Long id) {
        return categoryRepository.findById(id).map(cat -> {
            cat.setEnabled(false);
            return save(cat);
        });
    }

    // @Transactional
    // public CategoryDto disableCategoryById(Long id) {
    // Optional<CategoryEntity> category = categoryRepository.findById(id);
    // if (category.isPresent()) {
    // CategoryEntity cat = category.get();
    // cat.setEnabled(false);
    // categoryRepository.save(cat);
    // log.info("Category with ID {} disabled successfully.", id);
    // return convertToDto(cat);
    // // return new CategoryMapper().toDto(cat); // Usar el mapeador para convertir
    // a DTO
    // } else {
    // log.warn("Category with ID {} not found.", id);
    // return null;
    // }
    // // return category;
    // }

    // @Transactional
    // @Override
    // public void deleteById(Long id) {
    // categoryRepository.deleteById(id);
    // }

    // private CategoryDto convertToDto(CategoryEntity category) {
    //     return new CategoryDto(
    //             category.isEnabled(),
    //             category.getName(),
    //             category.getProducts().stream()
    //                     .map(ProductEntity::getName)
    //                     .collect(Collectors.toList()));
    // }

}
