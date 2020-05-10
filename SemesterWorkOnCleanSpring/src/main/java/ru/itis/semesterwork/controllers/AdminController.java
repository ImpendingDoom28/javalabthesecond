package ru.itis.semesterwork.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView getAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        return modelAndView;
    }
}
