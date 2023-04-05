package com.example.demo1.controllers;


import com.example.demo1.dto.GreetingMessage;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MainController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public GreetingMessage getGreeting(@RequestParam(value = "name",defaultValue = "World") String name){
        GreetingMessage greetingMessage = new GreetingMessage();
        greetingMessage.setId(counter.incrementAndGet());
        greetingMessage.setMessage(String.format(template, name));
        return greetingMessage;
    }


}
