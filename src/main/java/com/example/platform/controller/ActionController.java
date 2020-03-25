package com.example.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ActionController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "login.html";
    }
}
