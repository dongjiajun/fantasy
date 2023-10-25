package com.fantasy.brace.graphic.plat;


import com.fantasy.brace.common.datatype.AccessNodeList;
import com.fantasy.brace.common.rules.NetworkAccessProcessing;
import com.fantasy.brace.constant.StateConstant;
import com.fantasy.brace.listener.ConfigAccessListener;
import com.fantasy.brace.listener.event.FantasyEvent;
import com.fantasy.brace.listener.listeners.AccessListener;
import com.fantasy.brace.network.NetWorkAccessible;
import com.fantasy.brace.network.SimplePlatServer;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;

/**
 * 抽象的可网络连接的图（目前基于 可被网络接入，遵循接入连接规范，连接状态监听 三个维度）
 *
 * @author DongJiaJun
 */
public abstract class AbstractNetWorkAccessiblePlat extends AbstractPlat implements ConfigAccessListener, NetWorkAccessible, NetworkAccessProcessing {
    /**
     * 接入到本地网络的节点列表
     */
    protected AccessNodeList accessNodeList;

    /**
     * 管理连接的服务器(后续版本要改为nio的实现方式),在第二个版本改为用线程池实现的线程来管理我们的创建线程的逻辑
     */
    protected SimplePlatServer userConnectServer;


    /**
     * 无参构造，初始化连接节点列表
     */
    public AbstractNetWorkAccessiblePlat() {
        accessNodeList = new AccessNodeList();
    }


    /**
     * 持有用户表示，添加到连接设备列表
     *
     * @param identity 以 “ip地址+":"+端口号” 组成的身份标识
     * @param socket   用户的连接
     * @return 表示添加是否成功的布尔值
     */
    @Override
    public boolean addIntoAccessList(String identity, Socket socket) {
        accessNodeList.putIfAbsent(identity, socket);
        fireAccessNodeChanged("新用户(" + identity + ")加入");
        return true;
    }

    /**
     * 从连接列表中删除用户
     *
     * @param identity 以 “ip地址+":"+端口号” 组成的身份标识
     * @return 表示移除是否成功的布尔值
     */
    @Override
    public boolean removeFromAccessList(String identity) {
        accessNodeList.remove(identity);
        fireAccessNodeChanged("用户(" + identity + ")" + "离开");
        return true;
    }

    /**
     * 如果用户存在，更新该用户信息
     *
     * @param identity 以 “ip地址+":"+端口号” 组成的身份标识
     * @param socket   用户的连接
     * @return 表示更新是否成功的布尔值
     */
    @Override
    public boolean updateWhenExistIn(String identity, Socket socket) {
        accessNodeList.put(identity, socket);

        fireAccessNodeChanged("用户(" + identity + ")" + "连接已更新");
        return true;
    }

    @Override
    public void setAccessListener(AccessListener listener) {
        HashSet accessListeners = this.getListenerCollector().get("AccessListener");
        if (accessListeners == null) {
            accessListeners = new HashSet<AccessListener>();
            getListenerCollector().put("AccessListener", accessListeners);
        }
        accessListeners.add(listener);

    }

    @Override
    public void removeAccessListener(AccessListener listener) {
        HashSet userConnectListeners = this.getListenerCollector().get("AccessListener");
        if (userConnectListeners == null) {
            return;
        }
        getListenerCollector().remove("AccessListener");
    }

    /**
     * 响应连接节点状态的转变
     *
     * @param description 关于本次状态转变的描述
     */
    private void fireAccessNodeChanged(String description) {
        if (getListenerCollector() == null) {
            return;
        }
        FantasyEvent event = new FantasyEvent(this, "AccessListener", description);
        notifyListeners(event);
    }

    @Override
    public synchronized boolean allowNetworkAccess(int port) throws IOException {
        if (userConnectServer == null) {
            userConnectServer = new SimplePlatServer(7001, this, this.getClass().getSimpleName());
            userConnectServer.setClassType(this.getClass().getSimpleName());
        }

        setState("NETWORK", StateConstant.NETWORK_READY);

        return true;
    }

    @Override
    public boolean isNetWorkAccessibleReady(int port) {
        return getCurrentStateCode("NETWORK") == StateConstant.NETWORK_READY;
    }

    @Override
    public boolean openConnection() {
        if (userConnectServer != null
                && userConnectServer.getServerSocket() != null
                && userConnectServer.getServerSocket().getLocalPort() == userConnectServer.getPort()) {

            new Thread(userConnectServer).start();
            return true;
        } else {
            // throw new UnsupportedOperationException("校验未通过，当前无法启用服务器并执行连接监听");
            System.out.println("校验未通过，当前无法启用服务器并执行连接监听,处于离线状态...");
            return false;
        }

    }
}
