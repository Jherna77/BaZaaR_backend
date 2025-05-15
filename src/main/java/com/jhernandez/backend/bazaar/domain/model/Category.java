package com.jhernandez.backend.bazaar.domain.model;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.ErrorCode;

public class Category {

    private Long id;
    private Boolean enabled;
    private String name;
    private String imageUrl;

    public Category(Long id, String name, String imageUrl, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void enable() throws CategoryException {
        if (this.enabled)
            throw new CategoryException(ErrorCode.CATEGORY_ALREADY_ENABLED);
        this.enabled = true;
    }
    
    public void disable() throws CategoryException {
        if (!this.enabled)
            throw new CategoryException(ErrorCode.CATEGORY_ALREADY_DISABLED);
        this.enabled = false;
    }

}
