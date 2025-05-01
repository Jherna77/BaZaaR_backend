package com.jhernandez.backend.bazaar.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    UserServicePort userServicePort(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder) {
        return new UserService(userRepositoryPort, passwordEncoder);
    }

    @Bean
    ProductServicePort productServicePort(
        ProductRepositoryPort productRepositoryPort, ImageServicePort imageServicePort,
        UserServicePort userServicePort) {
        return new ProductService(productRepositoryPort, imageServicePort, userServicePort);
    }

    @Bean
    CategoryServicePort categoryServicePort(CategoryRepositoryPort categoryRepositoryPort, ImageServicePort imageServicePort) {
        return new CategoryService(categoryRepositoryPort, imageServicePort);
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
