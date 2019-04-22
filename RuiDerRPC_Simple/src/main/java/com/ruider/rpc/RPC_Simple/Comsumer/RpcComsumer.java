package com.ruider.rpc.RPC_Simple.Comsumer;
/**
 * RPC客户端调用服务接口，获取远程服务代理对象
 */

import com.ruider.RPC_Simple.Comsumer.InvokerHandle.InvokerHandle;

import java.lang.reflect.Proxy;

public class RpcComsumer {
    public static <T> T getService(Class<T> clazz , String ip , int port) {
        System.out.println("客户端开始获取服务端的服务");
        InvokerHandle invokerHandle = new InvokerHandle(ip,port);
        return (T)Proxy.newProxyInstance(RpcComsumer.class.getClassLoader() , new Class<?>[] {clazz}, invokerHandle);
    }
}
