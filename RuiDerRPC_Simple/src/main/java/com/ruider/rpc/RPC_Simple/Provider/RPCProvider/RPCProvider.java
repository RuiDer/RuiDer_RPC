package com.ruider.rpc.RPC_Simple.Provider.RPCProvider;

import com.ruider.RPC_Simple.Provider.ServiceThread.ServiceThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RPCProvider {

    //维护所有服务端的列表
    private static List<Object> serviceList = new ArrayList<>();

    //暴露服务，添加服务，与客户端建立socket连接，客户端数据处理，反射调用本地方法，返回客户端结果
    public static void export (int port , Object... services) {
        serviceList = Arrays.asList(services);     //暴露服务
        Socket client = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("provider启动server套接字socket");
            while (true) {
                client = serverSocket.accept();
                System.out.println("provider获取到客户端socket连接");
                new Thread(new ServiceThread(client , serviceList)).start();
            }
        }
        catch (IOException  e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (client != null) client.close();
                if (serverSocket != null) serverSocket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
