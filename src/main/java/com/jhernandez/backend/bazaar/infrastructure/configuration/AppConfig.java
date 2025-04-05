package com.jhernandez.backend.bazaar.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.jhernandez.backend.bazaar.application.port.*;
import com.jhernandez.backend.bazaar.application.service.*;

@Configuration
@PropertySource("classpath:messages.properties")
public class AppConfig {

    @Bean
    UserServicePort userServicePort(UserRepositoryPort userRepositoryPort) {
        return new UserService(userRepositoryPort);
    }

    @Bean
    ProductServicePort productServicePort(ProductRepositoryPort productRepositoryPort) {
        return new ProductService(productRepositoryPort);
    }

    @Bean
    CategoryServicePort categoryServicePort(CategoryRepositoryPort categoryRepositoryPort) {
        return new CategoryService(categoryRepositoryPort);
    }

}
