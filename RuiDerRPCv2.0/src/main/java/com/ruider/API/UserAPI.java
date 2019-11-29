package com.ruider.API;

import com.ruider.common.User;

/**
 * API提供服务的 User 基类
 */
public interface UserAPI {

    User getUser(Integer id);
}
