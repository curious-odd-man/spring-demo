package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginPageController {
    @GetMapping("login")
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping("register")
    public String registerPage(Model model) {
        return "register";
    }

    @PostMapping("login")
    @ResponseBody
    public String loginUser(@RequestParam("username") String userName, @RequestParam("password") String password, Model model) {
        return userName + password;
    }
}
