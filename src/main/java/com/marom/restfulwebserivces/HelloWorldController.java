package com.marom.restfulwebserivces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {


    @GetMapping("/hello-world")
    public HelloWorldBean helloWorld() {
        return new HelloWorldBean("Hello World!");
    }

    @GetMapping("/hello-world/{name}")
    public HelloWorldBean helloWorld(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
