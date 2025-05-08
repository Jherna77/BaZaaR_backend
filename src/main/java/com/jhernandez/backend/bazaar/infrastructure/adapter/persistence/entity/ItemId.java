package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ItemId implements Serializable {

    private Long orderId;
    private Long productId;

    // equals() y hashCode() deben estar implementados correctamente
}
