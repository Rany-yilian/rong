package com.rong.im.mapper;

import com.rong.im.Bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface UserMapper {

    User getUserByPhone(String phone);

    Map<String, Object> getById(Long id);

    Map<String, Object> getUser(@Param("phone") String phone);

    boolean insert(User user);

    boolean update(@Param("phone") String phone, @Param("pwd") String pwd, @Param("hash") String hash, @Param("create_time") long createTime);

    void delete(@Param("id") long id);
}
