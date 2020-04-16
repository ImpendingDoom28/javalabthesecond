package ru.itis.semesterwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.semesterwork.services.TokenService;

@Controller
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/auth")
    public ModelAndView verifyVerificationToken(@RequestParam(required = true, name = "confirmationToken") String token) {
        ModelAndView modelAndView = new ModelAndView();
        boolean isCorrectToken = tokenService.validateVerificationToken(token);
        modelAndView.setViewName("redirect:/login");
        if(!isCorrectToken) modelAndView.addObject("error", "Incorrect verification token");
        return modelAndView;
    }
}
