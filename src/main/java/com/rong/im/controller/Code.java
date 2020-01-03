package com.rong.im.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Code {

    @GetMapping("/code/send")
    public Map<String, Object> send(long phone) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("phone", phone);
        return param;
    }
}
