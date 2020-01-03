package com.rong.im.Service;

import com.rong.im.Bean.Sms;

import java.util.Map;


public interface SmsService {

    Map<String, Object> getSmsByPhone(Long phone);

    boolean addSms(Sms sms);

    boolean update(long status, String phone, String smcode, long createTime);

    boolean updateStatusByPhone(long status, String phone);
}
