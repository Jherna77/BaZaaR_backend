package com.jhernandez.backend.bazaar.domain.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class Product {

    private Long id;
    private Boolean enabled;
    private String name;
    private String description;
    private Double price;
    private Double shipping;
    private List<Category> categories;
    private List<String> imagesUrl;

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void removeCategory(Long categoryId) {
        for (Category category : this.categories) {
            if (category.getId().equals(categoryId)) {
                this.categories.remove(category);
                return;
            }
        }
        //Añadir categoria "Otros" si la lista de categorias está vacía
        // if (this.categories.isEmpty()) {
        //     Category otherCategory = new Category();
        //     otherCategory.setId(0L);
        //     otherCategory.setName("Otros");
        //     this.categories.add(otherCategory);
        // }
    }

    public void addImageUrl(String imageUrl) {
        this.imagesUrl.add(imageUrl);
    }

    public void removeImageUrl(String imageUrl) {
        this.imagesUrl.remove(imageUrl);
    }


    // public List<Category> getEnabledCategories() {
    //     return this.categories.stream().filter(Category::isEnabled).toList();
    // }
}

    // private BigDecimal discountPrice;
    // private BigDecimal discountRate;
    // private String imageUrl;
    // private String imageName;
    // private String imageType;
    // private Long stockQuantity;
    // private Long soldQuantity;
    // private Long createdAt;
    // private Long updatedAt;

