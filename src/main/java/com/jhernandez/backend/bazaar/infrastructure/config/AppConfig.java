package com.jhernandez.backend.bazaar.infrastructure.config;

// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// import com.jhernandez.backend.bazaar.application.services.CategoryService;
// import com.jhernandez.backend.bazaar.application.usecases.CreateCategoryUseCaseImpl;
// import com.jhernandez.backend.bazaar.application.usecases.DeleteCategoryUseCaseImpl;
// import com.jhernandez.backend.bazaar.application.usecases.RetrieveCategoryUseCaseImpl;
// import com.jhernandez.backend.bazaar.application.usecases.UpdateCategoryUseCaseImpl;
// import com.jhernandez.backend.bazaar.domain.ports.out.CategoryRepositoryPort;

@Configuration
@PropertySource("classpath:messages.properties")
public class AppConfig {

    // @Bean
    // public CategoryService categoryService(CategoryRepositoryPort categoryRepositoryPort) {
    //     return new CategoryService(
    //         new CreateCategoryUseCaseImpl(categoryRepositoryPort),
    //         new RetrieveCategoryUseCaseImpl(categoryRepositoryPort),
    //         new UpdateCategoryUseCaseImpl(categoryRepositoryPort),
    //         new DeleteCategoryUseCaseImpl(categoryRepositoryPort));
    // }

}
