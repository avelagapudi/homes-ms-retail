package com.tenx.ms.retail.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class sampleController {

    @RequestMapping(value="/hello" , produces="text/plain")
    @ResponseBody
    public String helloWorld() {
        return "Hello World Test!";
    }
}
