package com.rong.im.Service;

import com.rong.im.Bean.User;

import java.util.Map;

public interface UserService {

    User getUserByPhone(long phone);

    boolean addUser(User user);

    Map<String,Object> getUser(String phone);

    Map<String,Object> getById(Long id);
}
