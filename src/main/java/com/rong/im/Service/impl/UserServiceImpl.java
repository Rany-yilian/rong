package com.rong.im.Service.impl;

import com.rong.im.Bean.User;
import com.rong.im.Service.UserService;
import com.rong.im.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UserMapper userMapper;

    @Override
    public User getUserByPhone(long phone){
        User user = userMapper.getUserByPhone(phone+"");
        if(null!=user){
            return user;
        }
        return null;
    }

    @Override
    public boolean addUser(User user){
        return userMapper.insert(user);
    }


}
