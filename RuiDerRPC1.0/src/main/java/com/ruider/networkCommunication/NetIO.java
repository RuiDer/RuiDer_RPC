package com.ruider.networkCommunication;

import com.ruider.common.ServiceInformation;

/**
 * BIO服务
 */
public interface NetIO {

    /**
     * 客户端发送请求到其他服务端
     * @param serviceInformation
     * @return
     */
    Object send (ServiceInformation serviceInformation);

    /**
     * 本机作为服务端接受请求并且处理请求
     * @param port
     */
    void recv (int port);
}
