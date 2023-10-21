package com.fantasy.brace.network.server;


import com.fantasy.brace.common.datatype.AccessNodeList;
import com.fantasy.brace.common.datatype.ListenerCollector;
import com.fantasy.brace.common.rules.NetworkAccessProcessing;
import com.fantasy.brace.listener.event.FantasyEvent;

import java.net.Socket;
import java.rmi.AccessException;
import java.util.List;

/**
 * 抽象的可网络连接的图服务器
 *
 * @author DongJiaJun
 */
public abstract class AbstractAccessiblePlatServer extends AbstractServer implements NetworkAccessProcessing {

    /**
     * 监听器收集器
     */
    private ListenerCollector listenerCollector;

    /**
     * 接入的设备列表
     */
    private AccessNodeList accessNodeList;


    public AbstractAccessiblePlatServer(ListenerCollector listenerCollector, AccessNodeList accessNodeList) {
        this.listenerCollector = listenerCollector;
        this.accessNodeList = accessNodeList;
    }

    public AbstractAccessiblePlatServer(ListenerCollector listenerCollector) {
        this.listenerCollector = listenerCollector;
        this.accessNodeList = new AccessNodeList();
    }

    public AbstractAccessiblePlatServer(AccessNodeList accessNodeList) {
        this.listenerCollector = new ListenerCollector();
        this.accessNodeList = accessNodeList;
    }

    public ListenerCollector getListenerCollector() {
        return this.listenerCollector;
    }


    @Override
    public boolean addToAccessNodeList(String identity, Socket socket) {
        accessNodeList.putIfAbsent(identity, socket);
        fireAccessNodeListChanged("新用户(" + identity + ")加入");
        return true;
    }

    @Override
    public boolean removeFromAccessNodeList(String identity) {
        accessNodeList.remove(identity);
        fireAccessNodeListChanged("用户(" + identity + ")" + "离开");
        return true;
    }

    @Override
    public boolean updateWhenExistIn(String identity, Socket socket) {
        accessNodeList.put(identity, socket);

        fireAccessNodeListChanged("用户(" + identity + ")" + "连接已更新");
        return true;
    }

    /**
     * 待定注释
     *
     * @param identity 身份标识
     */
    abstract protected void fireAccessNodeListChanged(String identity);

    /**
     * 待定注释
     *
     * @param event 事件
     */
    abstract protected void notifyListeners(FantasyEvent event);
}
