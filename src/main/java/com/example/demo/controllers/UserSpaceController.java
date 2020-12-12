package com.example.demo.controllers;

import com.example.demo.util.FrontedParamsParsing;
import com.github.curiousoddman.rgxgen.RgxGen;
import com.github.curiousoddman.rgxgen.iterators.StringIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

@Controller
public class UserSpaceController {
    Logger logger = LogManager.getLogger(UserSpaceController.class);

    @RequestMapping("user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        logger.info("Received user info:{}", principal);
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @RequestMapping("/user/overview")
    public String userHome() {
        return "UserHome";
    }

    @GetMapping("user/generate")
    public StreamingResponseBody generate(@RequestParam Map<String, String> allRequestParams) {
        logger.info("Request params {}", allRequestParams);
        String pattern = allRequestParams.get("pattern");
        int amount = FrontedParamsParsing.getIntParam(allRequestParams, "amount");
        boolean unique = FrontedParamsParsing.getBooleanParam(allRequestParams, "is-unique");
        boolean generateMatching = FrontedParamsParsing.getBooleanParam(allRequestParams, "is-matching");
        logger.info("Parsed params '{}', '{}', '{}', '{}'", pattern, amount, unique, generateMatching);
        return out -> {
            RgxGen rgxGen = new RgxGen(pattern);
            Supplier<String> valueSupplier;
            if (unique) {
                StringIterator stringIterator = rgxGen.iterateUnique();
                valueSupplier = stringIterator::next;
            } else {
                if (generateMatching) {
                    valueSupplier = rgxGen::generate;
                } else {
                    valueSupplier = rgxGen::generateNotMatching;
                }
            }

            byte[] newlineBytes = "\n".getBytes();

            for (int i = 0; i < amount; i++) {
                String value = valueSupplier.get();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                out.write(value.getBytes());
                out.write(newlineBytes);
                out.flush();
            }

        };
    }
}
