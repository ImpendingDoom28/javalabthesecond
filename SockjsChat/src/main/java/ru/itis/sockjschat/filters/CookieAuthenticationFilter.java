package ru.itis.sockjschat.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;
import ru.itis.sockjschat.models.User;
import ru.itis.sockjschat.repositories.UsersRepository;
import ru.itis.sockjschat.repositories.UsersRepositoryImpl;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class CookieAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("IN FILTER");
        Cookie[] cookies = ((HttpServletRequest)request).getCookies();
        for(Cookie cookie: cookies) {
            if(cookie.getName().equals("USER_CHECK")) {
                if(cookie.getValue() != null) {
                    chain.doFilter(request, response);
                } else {
                    response.getWriter().println("You're not authorized!!! Proceed to /login to log in system.");
                }
            }
        }
    }
}
