package ru.itis.sockjschat.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

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
        boolean isUserCheckNotNull = false;
        boolean isXAuthNotNull = false;
        if(cookies == null) response.getWriter().println("You're not authorized!!! Proceed to /login to log in system.");
        assert cookies != null;
        for(Cookie cookie: cookies) {
            if(cookie.getName().equals("USER_CHECK")) {
                if(cookie.getValue() != null) {
                    isUserCheckNotNull = true;
                } else {
                    response.getWriter().println("You're not authorized!!! Proceed to /login to log in system.");
                }
            } else if(cookie.getName().equals("X-Authorization")) {
                if(cookie.getValue() != null) {
                    isXAuthNotNull = true;
                } else {
                    response.getWriter().println("You're not authorized!!! Proceed to /login to log in system.");
                }
            }
        }
        if (isUserCheckNotNull && isXAuthNotNull) {
            chain.doFilter(request, response);
        }
    }
}
