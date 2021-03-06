package com.rong.im.Service.impl;

import com.rong.im.Bean.Sms;
import com.rong.im.Bean.User;
import com.rong.im.Service.SmsService;
import com.rong.im.Service.UserService;
import com.rong.im.mapper.SmsMapper;
import com.rong.im.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SmsServiceImple implements SmsService {

    @Autowired(required = false)
    SmsMapper smsMapper;

    @Override
    public Map<String, Object> getSmsByPhone(Long phone) {
        Map<String, Object> sms = smsMapper.getSmsByPhone(phone + "");
        if (null != sms) {
            return sms;
        }
        return null;
    }

    @Override
    public boolean addSms(Sms sms) {
        return smsMapper.insert(sms);
    }

    @Override
    public boolean update(long status, String phone, String smcode, long create_time) {
        return smsMapper.update(status, phone, smcode, create_time);
    }

    @Override
    public boolean updateStatusByPhone(long status, String phone) {
        return smsMapper.updateStatusByPhone(status, phone);
    }
}
