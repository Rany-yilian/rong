package com.rong.im.controller;

import com.rong.im.Bean.User;
import com.rong.im.Service.UserService;
import com.rong.im.utils.JsonUtils;
import com.rong.im.utils.PhoneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sms")
public class Sms {

    @Autowired
    UserService userService;

    @RequestMapping("/send")
    @ResponseBody
    public Map<String, Object> send(long phone){
        boolean isPhone = PhoneUtils.check(phone+"");
        if(!isPhone){
            return JsonUtils.render("0","手机号格式错误");
        }
        User user = userService.getUserByPhone(phone);
        if(user!=null){
            return JsonUtils.render("0","此手机号已注册");
        }
        Map<String,Object> param= new HashMap<String,Object>();
        param.put("phone",phone);
        return param;
    }
}
