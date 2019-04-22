package com.ruider.rpc.RPC_Simple.Comsumer.InvokerHandle;

import com.ruider.RPC_Simple.Object2Byte.Object2Byte;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;

public class InvokerHandle implements InvocationHandler {

    private int port;

    private String ip;

    public InvokerHandle (String ip , int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy , Method method , Object[] args) throws Throwable{
        Socket client = null;
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        client = new Socket(this.ip, this.port);
        System.out.println("客户端建立socket连接");
        HashMap<String,Object> map = new HashMap<>();
        //input = new ObjectInputStream(client.getInputStream());
        //output = new ObjectOutputStream(client.getOutputStream());
        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();
        try {
            System.out.println("客户端开始输出数据");
            /*
            output.writeObject(proxy.getClass().getInterfaces()[0]);
            System.out.println("客户端完成输出数据1");
            output.writeUTF(method.getName());
            System.out.println("客户端完成输出数据2");
            output.writeObject(method.getParameterTypes());
            System.out.println("客户端完成输出数据3");
            output.writeObject(args);
            System.out.println("客户端完成输出数据4");
            output.flush();
            */
            map.put("serviceClass", proxy.getClass().getInterfaces()[0]);
            map.put("methodName", method.getName());
            map.put("parameterType", method.getParameterTypes());
            map.put("args", args);
            byte[] bytes = Object2Byte.toByteArray(map);
            out.write(bytes);
            out.flush();
            System.out.println("客户端完成输出数据");
            byte[] bytess = new byte[1024];
            int flag = in.read(bytess);
            while (flag !=-1){
                flag = in.read(bytess);
            }
            Object result = Object2Byte.toObject(bytess);
            System.out.println("客户端获取到服务端返回的结果："+result);
            if (result instanceof Throwable) throw (Throwable) result;
            return result;
        }
        finally {
            try {
                if (client != null) client.close();
                /*if (input != null) input.close();
                if (output != null) output.close();*/
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
