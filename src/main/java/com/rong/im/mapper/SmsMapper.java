package com.rong.im.mapper;

import com.rong.im.Bean.Sms;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SmsMapper {

    Sms getSmsByPhone(String phone);

    boolean insert(Sms sms);

    boolean update(@Param("phone") String phone,@Param("smcode") String smcode,@Param("create_time") long createTime);
}
