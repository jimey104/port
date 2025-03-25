package edu.du.module1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/module1")
public class Module1Controller {

    @GetMapping({"", "/"})
    public String index() {
        return "module1/index";
    }
} 