package com.rong.im.controller;

import com.rong.im.utils.JsonUtils;
import com.rong.im.utils.PhoneUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sms")
public class Sms {

    @RequestMapping("/send")
    @ResponseBody
    public Map<String, Object> send(long phone){
        boolean isPhone = PhoneUtils.check(phone+"");
        if(!isPhone){
            return JsonUtils.render("0","is not valid phone");
        }
        Map<String,Object> param= new HashMap<String,Object>();
        param.put("phone",phone);
        return param;
    }
}
