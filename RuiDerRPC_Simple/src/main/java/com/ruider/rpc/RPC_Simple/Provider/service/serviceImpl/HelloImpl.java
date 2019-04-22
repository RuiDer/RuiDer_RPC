package com.ruider.rpc.RPC_Simple.Provider.service.serviceImpl;

import com.ruider.rpc.RPC_Simple.Provider.service.HelloService;

public class HelloImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        System.out.println("say Hello!"+name);
        return "Hello!"+name;
    }
}
