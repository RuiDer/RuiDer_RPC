package com.ruider.client;

import com.ruider.Invoker.InvokerUtils;
import com.ruider.RPC.RPC;
import com.ruider.common.User;
import com.ruider.common.UserApi;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Client {
    //本地调用远程服务
    public  static  void  main(String args[]){
        UserApi userApi = (UserApi) RPC.rpc(UserApi.class, "127.0.0.1", 9000);
        User user = userApi.getUser(1);
        System.out.println("本地输出远程调用结果：" + user.toString());
    }

}

