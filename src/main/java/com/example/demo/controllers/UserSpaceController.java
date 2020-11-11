package com.example.demo.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class UserSpaceController {
    Logger logger = LogManager.getLogger(UserSpaceController.class);

    @RequestMapping("/user/overview")
    public String userHome(Model model) {
        logger.info(model);
        return "UserHome";
    }

    @GetMapping("user/generate")
    public String generate(@RequestParam Map<String, String> allRequestParams, Model model) {
        // FIXME: Still not getting all params from UI
        logger.info(allRequestParams);
        return "UserHome";
    }
}
