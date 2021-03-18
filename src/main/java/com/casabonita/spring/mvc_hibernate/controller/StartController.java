package com.casabonita.spring.mvc_hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartController {

    @RequestMapping("/")
    public String showStartPage(){
        return "start_page";
    }
}
