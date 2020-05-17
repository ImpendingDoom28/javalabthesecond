package ru.itis.semesterwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.semesterwork.events.OnRegisterSuccessEvent;
import ru.itis.semesterwork.forms.RegisterForm;
import ru.itis.semesterwork.models.User;
import ru.itis.semesterwork.services.RegisterService;
import ru.itis.semesterwork.services.UserService;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping("/register")
    public ModelAndView getRegister() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(WebRequest webRequest, RegisterForm registerForm) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            registerService.register(registerForm);
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/register");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
        String appUrl = webRequest.getContextPath();
        User user = userService.findByNickname(registerForm.getNickname());
        eventPublisher.publishEvent(new OnRegisterSuccessEvent(user, appUrl));
        modelAndView.setViewName("redirect:/confirm");
        modelAndView.addObject("email", user.getEmail());
        return modelAndView;
    }
}
