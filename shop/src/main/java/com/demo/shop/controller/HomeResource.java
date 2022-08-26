package com.demo.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @RequestMapping("/")
    public String helloController(){
        return "<h1>Hello Customer</h1>";
    }

    @GetMapping("/user")
    public String userController(){
        return "<h1>Hello Employee</h1>";
    }

    @GetMapping("/dept")
    public String deptManagerController(){
        return "<h1>Hello Department Manager</h1>";
    }

    @GetMapping("/store")
    public String storeManagerController(){
        return "<h1>Hello Store Manager</h1>";
    }
    
    
}
