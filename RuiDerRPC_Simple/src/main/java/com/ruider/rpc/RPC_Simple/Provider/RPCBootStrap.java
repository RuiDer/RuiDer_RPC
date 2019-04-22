package com.ruider.rpc.RPC_Simple.Provider;
/**
 * RPC Provider 启动类
 */

import com.ruider.rpc.RPC_Simple.Provider.RPCProvider.RPCProvider;
import com.ruider.rpc.RPC_Simple.Provider.service.HelloService;
import com.ruider.rpc.RPC_Simple.Provider.service.serviceImpl.HelloImpl;

public class RPCBootStrap {
    public static void main (String[] args) {
        HelloService hello = new HelloImpl();
        RPCProvider.export(20016 , hello);
    }
}
