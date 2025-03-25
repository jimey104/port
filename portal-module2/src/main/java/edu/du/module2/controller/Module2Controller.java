package edu.du.module2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/module2")
public class Module2Controller {

    @GetMapping({"", "/"})
    public String index() {
        return "module2/index";
    }
} 