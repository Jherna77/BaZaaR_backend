package com.jhernandez.backend.bazaar.domain.ports.out;

import com.jhernandez.backend.bazaar.domain.ports.in.CreateCategoryUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.DeleteCategoryUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.RetrieveCategoryUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.UpdateCategoryUseCase;

// This interface combines all the use cases related to category management.
// It extends the individual use case interfaces to provide a single entry point for category-related operations.
public interface CategoryServicePort extends
        CreateCategoryUseCase, RetrieveCategoryUseCase, UpdateCategoryUseCase, DeleteCategoryUseCase {

}
