package com.demo.springboot.SpringBootApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/mycontroller")
public class HelloController {

    @RequestMapping("/hi")
    public String greet(){
        return "Hi";
    }


}
