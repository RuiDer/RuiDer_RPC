package com.ruider.rpc.RPC_Simple.Provider.ServiceThread;
/**
 * 每个客户端socket连接交给对应的一个线程处理RPC服务调用，以及方法结果的返回
 */

import com.ruider.RPC_Simple.Object2Byte.Object2Byte;

import java.io.*;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class ServiceThread implements Runnable{

    private Socket client;

    private List<Object> serviceList;

    public ServiceThread (Socket client , List<Object> serviceList) {
        this.client = client;
        this.serviceList = serviceList;
    }

    @Override
    public void run () {
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        try {
            System.out.println("服务端线程开始处理客户端数据");
            /*input = new ObjectInputStream(client.getInputStream());
            output = new ObjectOutputStream(client.getOutputStream());
            Class serviceClass = (Class)input.readObject();*/
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            System.out.println("provider服务端开始接受数据");
            byte[] bytes = new byte[1024];
            int flag = in.read(bytes);
            while (flag !=-1){
                flag = in.read(bytes);
            }
            System.out.println("provider服务端完成接受数据");
            HashMap<String, Object> map = (HashMap<String, Object>) Object2Byte.toObject(bytes);
            Class serviceClass = (Class)map.get("serviceClass");
            Object obj = findService(serviceClass);
            if (obj == null) {
                /*
                output.writeObject(serviceClass.getName()+"服务不存在");
                System.out.println(serviceClass.getName()+"服务不存在");
                */
                String answer = serviceClass.getName()+"服务不存在";
                byte[] bytess = Object2Byte.toByteArray(answer);
                out.write(bytess);
                out.flush();
                System.out.println(serviceClass.getName()+"服务不存在");
            }
            else {
                try {
                    System.out.println(serviceClass.getName()+"服务存在");
                    /*String methodName = input.readUTF();
                    Class<?>[] parameterType = (Class<?>[])input.readObject();
                    Object[] arguments = (Object[])input.readObject();
                    */
                    System.out.println("执行客户端请求的方法");
                    String methodName = (String)map.get("methodName");
                    Class<?>[] parameterType = (Class<?>[])map.get("parameterType");
                    Method method = obj.getClass().getMethod(methodName,parameterType);
                    Object[] args = (Object[])map.get("args");
                    Object result = method.invoke(obj,args);
                    out.write(Object2Byte.toByteArray(result));
                }
                catch (Exception e) {
                    output.writeObject(e);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (input != null) input.close();
                if (output != null) output.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Object findService(Class serviceClass) {
        for (Object obj : serviceList) {
             boolean isFather = serviceClass.isAssignableFrom(obj.getClass());
             if (isFather) {
                 return obj;
             }
         }
         return null;
    }

}
