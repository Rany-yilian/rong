package com.rong.im.mapper;

import com.rong.im.Bean.Sms;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface SmsMapper {

    Map<String, Object> getSmsByPhone(String phone);

    boolean insert(Sms sms);

    boolean update(@Param("status") long status, @Param("phone") String phone, @Param("smcode") String smcode, @Param("create_time") long createTime);

    boolean updateStatusByPhone(@Param("status") long status, @Param("phone") String phone);
}
