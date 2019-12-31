package com.rong.im.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Index {

    @GetMapping("/index/send")
    @ResponseBody
    public Map<String, Object> send(long phone){
        Map<String,Object> param= new HashMap<String,Object>();
        param.put("phone",phone);
        return param;
    }
}
