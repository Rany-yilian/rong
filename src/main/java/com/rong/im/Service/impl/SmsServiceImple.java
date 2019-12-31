package com.rong.im.Service.impl;

import com.rong.im.Bean.Sms;
import com.rong.im.Bean.User;
import com.rong.im.Service.SmsService;
import com.rong.im.Service.UserService;
import com.rong.im.mapper.SmsMapper;
import com.rong.im.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImple implements SmsService {

    @Autowired(required = false)
    SmsMapper smsMapper;

    @Override
    public Sms getSmsByPhone(Long phone){
        Sms sms = smsMapper.getSmsByPhone(phone+"");
        if(null!=sms){
            return sms;
        }
        return null;
    }

    @Override
    public boolean addSms(Sms sms){
        return smsMapper.insert(sms);
    }

    @Override
    public boolean update(String phone,String smcode,long create_time){
        return smsMapper.update(phone,smcode,create_time);
    }
}
