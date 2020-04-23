package ru.itis.sockjschat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.sockjschat.forms.LoginForm;
import ru.itis.sockjschat.services.LoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(LoginForm loginForm, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            loginService.login(loginForm);
            Cookie cookie = new Cookie("X-Authorization", "123450D");
            cookie.setMaxAge(24*60*60);
            response.addCookie(cookie);
            Cookie userCookie = new Cookie("USER_CHECK", loginForm.getLogin());
            userCookie.setMaxAge(24*60*60);
            response.addCookie(userCookie);
            modelAndView.setViewName("redirect:/rooms");
        } catch(Exception e) {
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
    }
}
