package com.jhernandez.backend.bazaar.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.jhernandez.backend.bazaar.application.port.*;
import com.jhernandez.backend.bazaar.application.service.*;

/**
 * Spring configuration class for defining beans and application properties.
 * This class is responsible for creating and configuring the application services.
 */
@Configuration
@PropertySource("classpath:messages.properties")
public class AppConfig {

    @Bean
    UserServicePort userServicePort(UserRepositoryPort userRepositoryPort) {
        return new UserService(userRepositoryPort);
    }

    @Bean
    ProductServicePort productServicePort(ProductRepositoryPort productRepositoryPort, ImageStoragePort imageStoragePort) {
        return new ProductService(productRepositoryPort, imageStoragePort);
    }

    @Bean
    CategoryServicePort categoryServicePort(CategoryRepositoryPort categoryRepositoryPort, ImageStoragePort imageStoragePort) {
        return new CategoryService(categoryRepositoryPort, imageStoragePort);
    }

    @Bean
    UserRoleServicePort userRoleServicePort(UserRoleRepositoryPort userRoleRepositoryPort) {
        return new UserRoleService(userRoleRepositoryPort);
    }

    @Bean
    ImageServicePort imageServicePort(ImageStoragePort imageStoragePort) {
        return new ImageService(imageStoragePort);
    }

}
