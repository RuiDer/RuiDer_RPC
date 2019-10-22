package com.ruider.server;

import com.ruider.common.User;
import com.ruider.common.UserApi;

public class UserService implements UserApi {

    @Override
    public User getUser(Integer id) {
        User user=new User();
        user.setName("RuiDer");
        user.setId(id);
        System.out.println("user = " + user.toString());
        return user;
    }
}