package com.jhernandez.backend.bazaar.domain.ports.out;

import com.jhernandez.backend.bazaar.domain.ports.in.CreateProductUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.DeleteProductUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.RetrieveProductUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.UpdateProductUseCase;

// This interface combines all the use cases related to product management.
// It extends the individual use case interfaces to provide a single entry point for product-related operations.
public interface ProductServicePort extends
        CreateProductUseCase, RetrieveProductUseCase, UpdateProductUseCase, DeleteProductUseCase {
}
