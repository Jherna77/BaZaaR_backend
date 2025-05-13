package com.jhernandez.backend.bazaar.domain.model;

public class Category {

    private Long id;
    private String name;
    private String imageUrl;
    private boolean enabled;

    public Category(Long id, String name, String imageUrl, boolean enabled) {
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

    public boolean isEnabled() {
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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
