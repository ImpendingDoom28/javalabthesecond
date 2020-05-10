package ru.itis.semesterwork.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.itis.semesterwork.scope.AdminBeanFactoryPostProcessor;

@Configuration
@Scope("admin")
public class AdminScopeConfig {

    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new AdminBeanFactoryPostProcessor();
    }
}
