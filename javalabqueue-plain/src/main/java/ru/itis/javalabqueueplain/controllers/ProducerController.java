package ru.itis.javalabqueueplain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProducerController {

    @GetMapping("/producer")
    public String getProducePage() {
        return "producer_page";
    }
}