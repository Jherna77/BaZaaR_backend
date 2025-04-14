package com.jhernandez.backend.bazaar.infrastructure.configuration;

public class Values {

    public static final String UPLOAD_DIR = "/bazaar/uploads/";

    // API Endpoints
    public static final String USERS = "/api/users";
    public static final String USER_ID = USERS + "/{id}";
    public static final String REGISTER = USERS + "/register";

    public static final String ROLES = "/api/roles";

    public static final String CATEGORIES = "/api/categories";
    public static final String CATEGORY_ID = CATEGORIES + "/{id}";

    public static final String PRODUCTS = "/api/products";
    public static final String PRODUCT_ID = PRODUCTS + "/{id}";
    public static final String PRODUCTS_CATEGORY_ID = PRODUCTS + "/category/{categoryId}";

    public static final String IMAGES = "/api/images";
    public static final String IMAGE_ID = IMAGES + "/{filename:.+}";

}