package ru.itis.semesterwork.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmController {

    @GetMapping("/confirm")
    public ModelAndView getConfirm(@RequestParam("email") String email) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("confirm");
        modelAndView.addObject("email", email);
        return modelAndView;
    }
}
