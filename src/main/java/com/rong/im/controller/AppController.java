package com.rong.im.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/app")
public class AppController {

    public Map<String, Object> submit(String name,String desc,String token){

        return null;
    }
}
