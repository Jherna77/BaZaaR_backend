package com.jhernandez.backend.bazaar.configuration;

import org.springframework.context.annotation.Configuration;
// import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;

// import com.jhernandez.backend.bazaar.entities.UserEntity;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer{

    // Este método muestra el id de los usuarios en la respuesta JSON
    // @Override
    // public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
    //     config.exposeIdsFor(UserEntity.class);
    // }
    

}
