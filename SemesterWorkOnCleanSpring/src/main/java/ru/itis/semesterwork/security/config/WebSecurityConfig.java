package ru.itis.semesterwork.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier(value = "customUserDetails")
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //подписывание страничек
        http.csrf().disable();
        http
                .authorizeRequests()
                    .antMatchers("/admin").hasAuthority("ADMIN")
                    .antMatchers("/login", "/register", "/sandbox/*", "/confirm", "/auth").permitAll()
                    .antMatchers("/api/1.0/login", "/api/1.0/register", "/api/1.0/sandbox").permitAll()
                    .anyRequest().authenticated();
        http
                .formLogin()
                    .usernameParameter("nickname")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/profile")
                    .failureUrl("/login?error")
                    .permitAll();
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter() {
//        return new SecurityContextHolderAwareRequestFilter();
//    }
}
