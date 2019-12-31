package com.rong.im.Service;

import com.rong.im.Bean.Sms;


public interface SmsService {

    Sms getSmsByPhone(Long phone);

    boolean addSms(Sms sms);

    boolean update(String phone,String smcode,long createTime);
}
