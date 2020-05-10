package ru.itis.semesterwork.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.GenericFilterBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@Scope("admin")
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("jwtAuthenticationFilter")
    private GenericFilterBean jwtFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //подписывание страничек
        http.csrf().disable();
        http.cors().and().logout().disable();
        http.formLogin().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //Сюда добавлять те url'ы, на которых не нужна проверка секьюрити,
        //в частности, проверку нашим jwt фильтром.
        web.ignoring().antMatchers( "/api/1.0/login", "/api/1.0/sandbox", "/api/1.0/register", "/static/locales/**");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));

        List<String> methodsAllowed = new ArrayList<>();
        methodsAllowed.add("HEAD");
        methodsAllowed.add("GET");
        methodsAllowed.add("POST");
        methodsAllowed.add("PUT");
        methodsAllowed.add("DELETE");
        methodsAllowed.add("PATCH");
        configuration.setAllowedMethods(methodsAllowed);

        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        List<String> headersAllowed = new ArrayList<>();
        headersAllowed.add("*");
        configuration.setAllowedHeaders(headersAllowed);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }



    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
