package ru.javabegin.micro.demo.countryidentification.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPageController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }
}