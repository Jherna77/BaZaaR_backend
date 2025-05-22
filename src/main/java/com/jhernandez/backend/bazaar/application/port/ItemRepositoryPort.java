package com.jhernandez.backend.bazaar.application.port;

import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.model.Item;

public interface ItemRepositoryPort {

    Optional<Item> saveItem(Item item);

}
