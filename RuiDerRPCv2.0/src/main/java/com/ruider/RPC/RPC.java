package com.ruider.RPC;
/**
 * RPC管理
 */

import com.ruider.Invoker.InvokerUtils;
import com.ruider.common.ServiceInformation;
import com.ruider.networkCommunication.NetIO;

public class RPC {

    /**
     * RPC调用
     * @param clazz
     * @return
     */
    public static Object rpc(final Class clazz, ServiceInformation serviceInformation, NetIO netIO){
        InvokerUtils invokerUtils = new InvokerUtils();
        return invokerUtils.getBean(clazz, serviceInformation, netIO);
    }
}
