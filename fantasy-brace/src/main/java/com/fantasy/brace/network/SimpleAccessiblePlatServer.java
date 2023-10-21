package com.fantasy.brace.network;


import com.fantasy.brace.common.datatype.AccessNodeList;
import com.fantasy.brace.common.datatype.ListenerCollector;
import com.fantasy.brace.listener.event.FantasyEvent;
import com.fantasy.brace.listener.listeners.FantasyListener;
import com.fantasy.brace.graphic.plat.AbstractNetWorkAccessiblePlat;
import com.fantasy.brace.network.handler.UserConnectHandler;
import com.fantasy.brace.network.server.AbstractAccessiblePlatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.EventListener;
import java.util.HashSet;

/**
 * 简单的图连接服务器
 *
 * @author DongJiaJun
 */
public class SimpleAccessiblePlatServer extends AbstractAccessiblePlatServer {

    private String classType;

    private final AbstractNetWorkAccessiblePlat plat;


    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public SimpleAccessiblePlatServer(AccessNodeList accessNodeList, ListenerCollector listenerCollector, int port, AbstractNetWorkAccessiblePlat plat) throws IOException {
        super(listenerCollector, accessNodeList);

        setPort(port);
        this.plat = plat;
        setClassType(null);

        serverSocket = new ServerSocket(port);
    }

    public SimpleAccessiblePlatServer(AccessNodeList accessNodeList, ListenerCollector listenerCollector, AbstractNetWorkAccessiblePlat plat) throws IOException {
        super(listenerCollector, accessNodeList);

        setPort(-1);
        this.plat = plat;
        setClassType(null);

        serverSocket = new ServerSocket(6666);
    }

    private void execute() throws IOException {
        System.out.println(getClassType() + "的用户服务器已启动....");
        for (; ; ) {
            Socket sock = serverSocket.accept();
            if (addToAccessNodeList(sock.getInetAddress().toString() + ":" + sock.getPort(), sock)) {
                // TODO: 2023/9/30 待替换为统一的池模式处理，不允许独立开线程，以方便资源管理 ...
                new Thread(new UserConnectHandler(sock, classType, this, plat)).start();
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

    @Override
    protected void fireAccessNodeListChanged(String identity) {
        if (getListenerCollector() == null) {
            return;
        }
        FantasyEvent event = new FantasyEvent(this, "AccessListener", identity);
        notifyListeners(event);
    }

    @Override
    protected void notifyListeners(FantasyEvent event) {
        HashSet<? extends EventListener> affectListeners = getListenerCollector().get(event.getType());

        if (affectListeners == null) {
            return;
        }
        for (Object affectListener : affectListeners) {
            FantasyListener fantasyListener = (FantasyListener) affectListener;
            fantasyListener.doEvent(event);
        }

    }
}

