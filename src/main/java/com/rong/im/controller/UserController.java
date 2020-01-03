package com.rong.im.controller;

import com.rong.im.Bean.User;
import com.rong.im.Service.MessageService;
import com.rong.im.Service.SmsService;
import com.rong.im.Service.UserService;
import com.rong.im.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    SmsService smsService;
    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

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

    @RequestMapping(value = "/login")
    @ResponseBody
    public Map<String, Object> login(String pwd,long phone){
        boolean isPhone = PhoneUtils.check(phone+"");
        if(!isPhone){
            return JsonUtils.render("0","手机号格式错误");
        }
        Map<String,Object> user = userService.getUser(phone+"");
        if(user==null){
            return JsonUtils.render("0","此手机号未注册");
        }
        String userPwd = String.valueOf(user.get("pwd"));
        String userHash = String.valueOf(user.get("hash"));
        if(!userPwd.equals(EncryptUtils.md5(pwd+userHash))){
            return JsonUtils.render("0","密码错误");
        }
        Map<String,Object> json = new HashMap<String, Object>();
        String id = String.valueOf(user.get("id"));
        try {
            String token = AesUtils.aesEncrypt(id);
            json.put("token",token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonUtils.render("1","登录成功",json);
    }

    @RequestMapping("/info")
    @ResponseBody
    public Map<String,Object> info(String token){
        try {
            String uid = String.valueOf(AesUtils.aesDecrypt(token));
            Map<String,Object> user = userService.getById(Long.valueOf(uid));
            Map<String,Object> json = new HashMap<String, Object>();
            json.put("msg_num",messageService.getCount(Long.valueOf(uid)));
            json.put("no_read_msg_num",messageService.getNoReadCount(Long.valueOf(uid)));
            String nickname = String.valueOf(user.get("nickname"));
            if(user.get("nickname")==null){
                nickname = "";
            }
            String avatar = String.valueOf(user.get("avatar"));
            if(user.get("avatar")==null){
                avatar = "";
            }
            json.put("nickname",nickname);
            json.put("avatar",avatar);
            return JsonUtils.render("1","获取成功",json);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
