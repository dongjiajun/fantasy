package com.fantasy.brace.network.server;


import com.fantasy.brace.applicable.graphic.plat.AbstractNetWorkAccessiblePlat;
import com.fantasy.brace.network.handler.UserConnectHandler;

import java.io.IOException;
import java.net.Socket;

/**
 * 简单的图连接服务器
 *
 * @author DongJiaJun
 */
public class SimpleNetWorkAccessiblePlatServer extends AbstractPlatServer {

    private String classType;

    private AbstractNetWorkAccessiblePlat netWorkAccessiblePlat;

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public AbstractNetWorkAccessiblePlat getNetWorkAccessiblePlat() {
        return netWorkAccessiblePlat;
    }

    protected void setNetWorkAccessiblePlat(AbstractNetWorkAccessiblePlat netWorkAccessiblePlat) {
        this.netWorkAccessiblePlat = netWorkAccessiblePlat;
    }

    public SimpleNetWorkAccessiblePlatServer() {
        netWorkAccessiblePlat = null;
    }

    public SimpleNetWorkAccessiblePlatServer(int port, AbstractNetWorkAccessiblePlat netWorkAccessiblePlat, String classType) throws IOException {
        super(port, netWorkAccessiblePlat);
        setNetWorkAccessiblePlat(netWorkAccessiblePlat);
        setClassType(classType);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

