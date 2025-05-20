package com.jhernandez.backend.bazaar.application.service;

import java.util.List;

import com.jhernandez.backend.bazaar.application.port.SaleServicePort;
import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.ErrorCode;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Item;

public class SaleService implements SaleServicePort {

    private final UserRepositoryPort userRepository;

    public SaleService(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public List<Item> getUserSales(Long userId) throws UserException {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND))
                .getSales();
    }
    
}
