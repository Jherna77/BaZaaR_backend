package com.jhernandez.backend.bazaar.infrastructure.configuration;

public class Values {

    public static final String UPLOAD_DIR = "/bazaar/uploads/";
    public static final String IMG_CONTENT_TYPE_OCTET = "application/octet-stream";
    public static final String DISABLED_ITEM = "(DISABLED) ";
    public static final String PUT_REQUEST = "PUT";
    public static final String ARG_PRODUCT = "product";
    public static final String ARG_CATEGORY = "category";
    public static final String ARG_IMAGE = "image";

    // API Endpoints
    public static final String AUTH = "/api/auth";
    
    public static final String USERS = "/api/users";
    public static final String USER_ID = USERS + "/{id}";
    public static final String REGISTER = USERS + "/register";

    public static final String ROLES = "/api/roles";

    public static final String CATEGORIES = "/api/categories";
    public static final String CATEGORIES_ENABLED = CATEGORIES + "/enabled";
    public static final String CATEGORY_ID = CATEGORIES + "/{id}";

    public static final String PRODUCTS = "/api/products";
    public static final String PRODUCTS_ENABLED = PRODUCTS + "/enabled";
    public static final String PRODUCT_ID = PRODUCTS + "/{id}";
    public static final String PRODUCTS_USER_ID = PRODUCTS + "/user/{userId}";
    public static final String PRODUCTS_CATEGORY_ID = PRODUCTS + "/category/{categoryId}";

    public static final String IMAGES = "/api/images";
    public static final String IMAGE_ID = IMAGES + "/{filename:.+}";

}