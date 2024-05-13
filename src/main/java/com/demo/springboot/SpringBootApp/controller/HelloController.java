package com.demo.springboot.SpringBootApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

//@RequestMapping("/mycontroller")
public class HelloController {

    @RequestMapping("/")
    public String greet(){
        return "Hi";
    }


    @RequestMapping("/user")
    public String userAccess(){
        return "user only";
    }


    @RequestMapping("/admin")
    public String adminAccess(){
        return "admin access";
    }



}
