package com.rong.im.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Sms {

    @GetMapping("/sms")
    @ResponseBody
    public String send(){
        return "send";
    }
}
