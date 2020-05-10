package ru.itis.semesterwork.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@Scope("admin")
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/locales/**")
                .addResourceLocations("/WEB-INF/locales/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
//    // добавили в наше приложение перехватчик запросов для локализации
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
//
//    // храним информацию о выбранном языке в куках
//    @Bean
//    public LocaleResolver localeResolver() {
//        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//        cookieLocaleResolver.setCookieName("localeInfo");
//        cookieLocaleResolver.setCookieMaxAge(60 * 60 * 24 * 365);
//        return cookieLocaleResolver;
//    }
//
//    // перехватчик настроек языка
//    // то есть если на сервер пришел запрос localhost:8080/login?lang=ru или ?lang=en, то этот перехватчик
//    // установит куку с нужным значением
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("lang");
//        return localeChangeInterceptor;
//    }
//
//    @Bean
//    public LocalValidatorFactoryBean getValidator() {
//        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//        bean.setValidationMessageSource(messageSource());
//        return bean;
//    }
//    // откуда читать ключи с языками
//    @Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:messages/messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }
//
//    @Override
//    public MessageCodesResolver getMessageCodesResolver() {
//        DefaultMessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
//        codesResolver.setMessageCodeFormatter(DefaultMessageCodesResolver.Format.POSTFIX_ERROR_CODE);
//        return codesResolver;
//    }

}
