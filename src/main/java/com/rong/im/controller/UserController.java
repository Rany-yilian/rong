package com.rong.im.controller;

import com.rong.im.Bean.User;
import com.rong.im.Service.SmsService;
import com.rong.im.Service.UserService;
import com.rong.im.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    SmsService smsService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register")
    @ResponseBody
    public Map<String, Object> register(String pwd,String code,long phone){
        boolean isPhone = PhoneUtils.check(phone+"");
        if(!isPhone){
            return JsonUtils.render("0","手机号格式错误");
        }
        User user = userService.getUserByPhone(phone);
        if(user!=null){
            return JsonUtils.render("0","此手机号已注册");
        }
        Map<String,Object> sms = smsService.getSmsByPhone(phone);
        Long expire = Long.valueOf(String.valueOf(sms.get("create_time")));
        long time = TimeUtils.getCurrentTime();
        if((time-expire)>60){
            return JsonUtils.render("0","短信已过期");
        }
        int status = Integer.valueOf(String.valueOf(sms.get("status")));
        if(status==2){
            return JsonUtils.render("0","短信已过期");
        }
        String smsCode = String.valueOf(sms.get("smcode"));
        if(!smsCode.equals(code)){
            return JsonUtils.render("0","验证码错误");
        }

        String hash = CodeUtils.getRandomString(5);
        String encryptPwd = EncryptUtils.md5(pwd+hash);
        user = new User();
        user.setPwd(encryptPwd);
        user.setCreateTime(time);
        user.setHash(hash);
        user.setPhone(phone+"");
        userService.addUser(user);
        smsService.updateStatusByPhone(2,phone+"");
        return JsonUtils.render("1","注册成功");
    }
}
