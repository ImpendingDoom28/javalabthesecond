package ru.itis.springemail.services;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RegisterService {

    List<String> register(String email, String password, String name, String surname, ServletContext context);
    
}
