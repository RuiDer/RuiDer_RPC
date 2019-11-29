package com.ruider.service;

import com.ruider.API.UserAPI;
import com.ruider.common.User;

public class UserService implements UserAPI {

    @Override
    public User getUser(Integer id) {
        User user=new User();
        user.setName("RuiDer");
        user.setId(id);
        System.out.println("user = " + user.toString());
        return user;
    }
}