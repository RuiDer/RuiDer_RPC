package com.ruider.rpc.RPC_Simple;
/**
 * RPC测试
 */

import com.ruider.RPC_Simple.Comsumer.RpcComsumer;
import com.ruider.RPC_Simple.Provider.service.HelloService;

public class RPCTest {
    public static void main (String[] args) {
        HelloService helloService = RpcComsumer.getService(HelloService.class , "localhost" , 20016);
        String result = helloService.sayHello("马和德");
        System.out.println(result);
    }
}
