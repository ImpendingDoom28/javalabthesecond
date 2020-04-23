package ru.itis.sockjschat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.sockjschat.forms.RegisterForm;
import ru.itis.sockjschat.services.RegisterService;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(RegisterForm registerForm) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            registerService.register(registerForm);
            modelAndView.setViewName("redirect:/login");
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("register");
        }
        return modelAndView;
    }
}
