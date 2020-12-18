package com.ba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/language")
public class LanguageController {

    @Autowired
    MessageSource messageSource;

    @GetMapping
    public String welcomeMessageByLocale(@RequestHeader("Accept-Language") String locale) {
        return messageSource.getMessage("app.welcome.message", null, new Locale(locale));
    }
}
