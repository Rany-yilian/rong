package com.rong.im.controller;

import com.rong.im.Bean.Sms;
import com.rong.im.Bean.User;
import com.rong.im.Service.SmsService;
import com.rong.im.Service.UserService;
import com.rong.im.utils.CodeUtils;
import com.rong.im.utils.JsonUtils;
import com.rong.im.utils.PhoneUtils;
import com.rong.im.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    UserService userService;
    @Autowired
    SmsService smsService;

    public Sms sm = new Sms();

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
        Sms sms = smsService.getSmsByPhone(phone);
        long time = TimeUtils.getCurrentTime();
        //生成短信验证码
        String code = CodeUtils.getRandomString(5);
        if(sms!=null){
            if((time-sms.getCreateTime())<60){
                long diff = 60-(time-sms.getCreateTime());
                return JsonUtils.render("0","请"+diff+"秒后再试");
            }
            smsService.update(phone+"",code,time);
        }else{
            sm.setSmcode(code);
            sm.setPhone(phone+"");
            sm.setCreateTime(time);
            smsService.addSms(sm);
        }

        return JsonUtils.render("1","发送成功");
    }
}
