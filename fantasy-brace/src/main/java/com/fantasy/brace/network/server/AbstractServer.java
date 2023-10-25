package com.fantasy.brace.network.server;

import java.io.IOException;
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
    private ServerSocket serverSocket;

    /**
     * 端口号
     */
    private int port;


    public AbstractServer() {
        serverSocket = null;
        setPort(-1);
    }

    public AbstractServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.port = port;
    }

    protected void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    protected synchronized void initServerSocket() throws IOException {
        if(this.port > 0 && this.serverSocket == null){
            this.serverSocket = new ServerSocket(port);
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

}
