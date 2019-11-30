package com.marom.restfulwebserivces.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/hello-world")
    public HelloWorldBean helloWorld() {
        return new HelloWorldBean("Hello World!");
    }

    @GetMapping("/hello-world/{name}")
    public HelloWorldBean helloWorld(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(value = "/goodMorning", produces = { "application/xml", "application/json" })
    public String goodMorning() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

}
