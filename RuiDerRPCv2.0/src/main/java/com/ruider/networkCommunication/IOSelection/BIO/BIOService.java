package com.ruider.networkCommunication.IOSelection.BIO;

import com.ruider.API.UserAPI;
import com.ruider.common.ServiceInformation;
import com.ruider.networkCommunication.NetIO;
import com.ruider.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 使用BIO通信
 */
public class BIOService implements NetIO {

    /**
     * 主机IP
     */
    private String url;
    /**
     * 端口
     */
    private int port;

    /**
     * 客户端socket
     */
    private Socket clientSocket;

    /**
     * 服务端socket
     */
    private ServerSocket serverSocket;

    public BIOService() {}

    public BIOService(String url, int port) {
        this.url = url;
        this.port = port;
    }

    /**
     * 客户端采用BIO通信方式发送请求信息
     * @param serviceInformation
     * @return
     */
    @Override
    public Object send(ServiceInformation serviceInformation) {

        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            this.clientSocket = new Socket(serviceInformation.getServiceURL(), serviceInformation.getPort());
            String className = serviceInformation.getClassName();
            String  methodName = serviceInformation.getMethod().getName();
            Class<?>[] parameterTypes = serviceInformation.getMethod().getParameterTypes();
            Object[] args = serviceInformation.getArgs();

            objectOutputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
            objectOutputStream.writeUTF(className);
            objectOutputStream.writeUTF(methodName);
            objectOutputStream.writeObject(parameterTypes);
            objectOutputStream.writeObject(args);

            objectInputStream = new ObjectInputStream(this.clientSocket.getInputStream());
            Object o = objectInputStream.readObject();
            System.out.println("Object = " + o);
            return o;
        }
        catch (Exception e) {
            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                }catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (objectOutputStream != null) {

                try {
                    objectOutputStream.close();
                }catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                }catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            return null;
        }
        finally {
            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                }catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (objectOutputStream != null) {

                try {
                    objectOutputStream.close();
                }catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                }catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

    /**
     * 服务端采用BIO通信获取数据
     * @return
     */
    @Override
    public void recv (int port) {

        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("启动远程服务监听...");
            //监听客户端发来消息
            while (true){
                Socket socket = serverSocket.accept();
                objectInputStream = new ObjectInputStream(socket.getInputStream());

                //客户端传输类名
                String className = objectInputStream.readUTF();
                String methodName = objectInputStream.readUTF();
                Class<?>[] parameterTypes = (Class<?>[])objectInputStream.readObject();
                Object[] arguments = (Object[]) objectInputStream.readObject();

                Class clazz = null;
                //服务匹配
                if(className.equals(UserAPI.class.getName())){
                    clazz = UserService.class;
                }


                Method method = clazz.getMethod(methodName,parameterTypes);
                Object result = method.invoke(clazz.newInstance(),arguments);

                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(result);
                objectOutputStream.flush();

                socket.close();
            }
        } catch (Exception e) {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (this.serverSocket != null) {
                    this.serverSocket.close();
                }
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (this.serverSocket != null) {
                    this.serverSocket.close();
                }
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
