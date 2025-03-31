package com.jhernandez.backend.bazaar.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder {
    private static ApplicationContext context;

    public SpringContextHolder(ApplicationContext applicationContext) {
        SpringContextHolder.context = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}