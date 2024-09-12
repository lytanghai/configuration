package com.init.configure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BeautifyController {

    private final static String DIR_PREFIX = "format/";

    @GetMapping("/beautify")
    public String showJSONPage() {
        return DIR_PREFIX + "beautify";
    }

}
