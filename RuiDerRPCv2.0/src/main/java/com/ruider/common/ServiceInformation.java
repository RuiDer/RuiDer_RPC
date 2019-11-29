package com.ruider.common;
/**
 * 被调用服务信息
 */

import java.io.Serializable;
import java.lang.reflect.Method;

public class ServiceInformation implements Serializable {

    /**
     * 被调用服务的主机ip
     */
    private String serviceURL;

    /**
     * 端口
     */
    private int port;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private Method method;

    /**
     * 方法参数
     */
    private Object[] args;

    public String getServiceURL() {
        return serviceURL;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
