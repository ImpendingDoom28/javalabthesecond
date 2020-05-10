package ru.itis.semesterwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.semesterwork.forms.LoginForm;
import ru.itis.semesterwork.services.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public ModelAndView getLogin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
//
//    @PostMapping("/login")
//    public ModelAndView login(@RequestBody LoginForm loginForm){
//        ModelAndView modelAndView = new ModelAndView();
//        return modelAndView;
//    }
}
