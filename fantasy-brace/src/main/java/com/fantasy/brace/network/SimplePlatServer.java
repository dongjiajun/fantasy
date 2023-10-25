package com.fantasy.brace.network;


import com.fantasy.brace.graphic.plat.AbstractNetWorkAccessiblePlat;
import com.fantasy.brace.network.handler.UserConnectHandler;
import com.fantasy.brace.network.server.AbstractPlatServer;

import java.io.IOException;
import java.net.Socket;

/**
 * 简单的图连接服务器
 *
 * @author DongJiaJun
 */
public class SimplePlatServer extends AbstractPlatServer {

    private String classType;

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public SimplePlatServer(int port, AbstractNetWorkAccessiblePlat netWorkAccessiblePlat, String classType) throws IOException {
        super(port, netWorkAccessiblePlat);
        setClassType(classType);
    }

    public SimplePlatServer(String classType) throws IOException {
        setClassType(classType);
    }

    private void execute() throws IOException {
        System.out.println(getClassType() + "的用户服务器已启动....");
        for (; ; ) {
            Socket sock = getServerSocket().accept();
            if (getNetWorkAccessiblePlat().addIntoAccessList(sock.getInetAddress().toString() + ":" + sock.getPort(), sock)) {
                // TODO: 2023/9/30 待替换为统一的池模式处理，不允许独立开线程，以方便资源管理 ...
                new Thread(new UserConnectHandler(sock,
                        classType,
                        getNetWorkAccessiblePlat(),
                        getNetWorkAccessiblePlat())).start();
            } else {
                System.out.println("注册用户到连接监听过程中异常");
            }
        }
    }

    @Override
    public void run() {
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

