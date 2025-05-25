package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Product;

public interface RetrievePreferencesUseCase {

    List<Product> getUserFavouriteProducts(Long userId) throws UserException;

    Boolean isUserFavouriteProduct(Long userId, Long productId) throws UserException, ProductException;

}
