package com.fantasy.brace.network.server;

import java.net.ServerSocket;

/**
 * 抽象的服务器类
 *
 * @author DongJiaJun
 */
public abstract class AbstractServer implements Runnable {

    /**
     * 基本的服务器插口
     */
    protected ServerSocket serverSocket;

    /**
     * 服务器端口号
     */
    private int port;


    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    protected int getPort() {
        return port;
    }

    protected void setPort(int port) {
        this.port = port;
    }


}
