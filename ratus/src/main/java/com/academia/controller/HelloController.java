package com.academia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping()
    public String HelloWordALL(){
        return "Hello, World!" + " - Para Todos";
    }

    @GetMapping("/user")
    public String HelloWordUser(){ return "Hello, World!" + " - Para User"; }

    @GetMapping("/admin")
    public String HelloWordAdmin() { return "Hello, World" + " - Apenas para Admin"; }
}
