package com.ruider.client;

import com.ruider.API.UserAPI;
import com.ruider.RPC.RPC;
import com.ruider.common.ServiceInformation;
import com.ruider.common.User;
import com.ruider.networkCommunication.IOSelection.BIO.BIOService;

public class Client {
    //本地调用远程服务
    public  static  void  main(String args[]){
        ServiceInformation serviceInformation = new ServiceInformation();
        serviceInformation.setServiceURL("127.0.0.1");
        serviceInformation.setPort(9000);
        serviceInformation.setClassName(UserAPI.class.getName());

        UserAPI userApi = (UserAPI) RPC.rpc(UserAPI.class, serviceInformation, new BIOService());
        User user = userApi.getUser(1);
        System.out.println("本地输出远程调用结果：" + user.toString());
    }

}

