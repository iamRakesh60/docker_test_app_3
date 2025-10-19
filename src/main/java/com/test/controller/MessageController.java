package com.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MessageController {

    // http://localhost:9090/
    @GetMapping("/")
    public String msg() {
        return "This is Test message";
    }

    // http://localhost:9090/
    @GetMapping("/message2")
    public String msg2() {
        return "This is 2nd Test message";
    }
}
