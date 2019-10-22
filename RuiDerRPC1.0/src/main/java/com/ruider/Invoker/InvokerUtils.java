package com.ruider.Invoker;

/**
 * 动态代理工厂，对相应的class生产其对应代理类
 */

import com.ruider.common.ServiceInformation;
import com.ruider.networkCommunication.BIOService;
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
     * 获取代理对象
     * @param T
     * @param url
     * @param port
     * @return
     */
    public Object getBean (Class T, String url, int port) {
        this.clazz = T;
        ServiceInformation serviceInformation = new ServiceInformation();
        serviceInformation.setServiceURL(url);
        serviceInformation.setPort(port);
        serviceInformation.setClassName(T.getName());
        this.serviceInformation = serviceInformation;
        return Proxy.newProxyInstance(T.getClassLoader(), new Class[]{T}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        NetIO BIO = new BIOService();
        this.serviceInformation.setMethod(method);
        this.serviceInformation.setArgs(args);
        return BIO.send(serviceInformation);
    }
}
