package com.jhernandez.backend.bazaar.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.models.Product;
import com.jhernandez.backend.bazaar.domain.ports.in.CreateProductUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.DeleteProductUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.RetrieveProductUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.UpdateProductUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.ProductServicePort;

import lombok.AllArgsConstructor;

// This class is responsible for implementing the ProductServicePort interface and delegating the calls to the use cases.
// It uses the use cases to perform the operations and returns the results to the controller.
@Service
@AllArgsConstructor
public class ProductService implements ProductServicePort{
    
    private final CreateProductUseCase createProductUseCase;
    private final RetrieveProductUseCase retrieveProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    
    @Override
    public Optional<Product> createProduct(Product product) throws ProductException {
        return createProductUseCase.createProduct(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) throws ProductException {
        return retrieveProductUseCase.getProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return retrieveProductUseCase.getAllProducts();
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product product) throws ProductException {
        return updateProductUseCase.updateProduct(id, product);
    }

    @Override
    public void deleteProduct(Long id) throws ProductException {
        deleteProductUseCase.deleteProduct(id);
    }

}
