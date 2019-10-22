package com.ruider.RPC;
/**
 * RPC管理
 */

import com.ruider.Invoker.InvokerUtils;

public class RPC {

    /**
     * RPC调用
     * @param clazz
     * @return
     */
    public static Object rpc(final Class clazz, String url, int port){
        InvokerUtils invokerUtils = new InvokerUtils();
        return invokerUtils.getBean(clazz, url, port);
    }
}
