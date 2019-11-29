package com.ruider.Invoker;

/**
 * 动态代理工厂，对相应的class生产其对应代理类
 */

import com.ruider.common.ServiceInformation;
import com.ruider.networkCommunication.NetIO;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class InvokerUtils implements InvocationHandler {

    /**
     * 调用服务类
     */
    private Class clazz;

    /**
     * 调用服务管理
     */
    private ServiceInformation serviceInformation;

    /**
     * RPC 远程通信方式
     */
    private NetIO netIO;

    /**
     * 获取代理对象
     * @param T
     * @param serviceInformation
     * @param netIO
     * @return
     */
    public Object getBean (Class T, ServiceInformation serviceInformation, NetIO netIO) {
        this.clazz = T;

        this.serviceInformation = serviceInformation;
        this.serviceInformation.setClassName(T.getName());

        this.netIO = netIO;
        return Proxy.newProxyInstance(T.getClassLoader(), new Class[]{T}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.serviceInformation.setMethod(method);
        this.serviceInformation.setArgs(args);
        return netIO.send(serviceInformation);
    }
}
