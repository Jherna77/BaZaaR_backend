package com.jhernandez.backend.bazaar.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
// import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

// import jakarta.validation.Validator;

@Configuration
// @ComponentScan
@Slf4j
public class ValidationConfig {

    // @Bean
    // public Validator validator() {
    //     log.info("Creating LocalValidatorFactoryBean bean");
    //     return new LocalValidatorFactoryBean();
    // }

    // @Bean
    // public MethodValidationPostProcessor methodValidationPostProcessor() {
    //     MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
    //     processor.setValidator(validator());
    //     return processor;
    // }
}

