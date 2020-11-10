package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
public class HomePageController {

    @Value("${welcome.message}")
    private String message;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("tasks", Arrays.asList("x", "y"));
        return "welcome";
    }
}
