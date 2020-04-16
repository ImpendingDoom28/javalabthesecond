package ru.itis.semesterwork.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class ChatController {

    @GetMapping("/support")
    public ModelAndView getChatPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("support");
        modelAndView.addObject("pageId", UUID.randomUUID().toString());
        return modelAndView;
    }
}
