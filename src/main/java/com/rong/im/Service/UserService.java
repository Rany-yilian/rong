package com.rong.im.Service;

import com.rong.im.Bean.User;

public interface UserService {

    User getUserByPhone(long phone);

    boolean addUser(User user);

}
