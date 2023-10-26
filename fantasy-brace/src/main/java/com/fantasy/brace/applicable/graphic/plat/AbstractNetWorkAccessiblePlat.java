package com.fantasy.brace.applicable.graphic.plat;


import com.fantasy.brace.common.Status;
import com.fantasy.brace.datatype.AccessNodeList;
import com.fantasy.brace.datatype.ListenerCollector;
import com.fantasy.brace.network.NetworkAccessProcessing;
import com.fantasy.brace.constant.StateConstant;
import com.fantasy.brace.listener.ConfigAccessListener;
import com.fantasy.brace.listener.event.FantasyEvent;
import com.fantasy.brace.listener.listeners.AccessListener;
import com.fantasy.brace.math.coordinate.CoordinateSystem;
import com.fantasy.brace.network.NetWorkAccessible;
import com.fantasy.brace.network.server.SimplePlatServer;

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
     * 管理连接的服务器
     */
    protected SimplePlatServer userConnectServer;


    public AbstractNetWorkAccessiblePlat() {
        accessNodeList = new AccessNodeList();
    }

    public AbstractNetWorkAccessiblePlat(int port) throws IOException {
        accessNodeList = new AccessNodeList();
        userConnectServer = new SimplePlatServer(port, this, this.getClass().getSimpleName());
    }

    public AbstractNetWorkAccessiblePlat(Status status, CoordinateSystem coordinateSystem, AccessNodeList accessNodeList) {
        super(status, coordinateSystem);
        this.accessNodeList = accessNodeList;
    }

    public AbstractNetWorkAccessiblePlat(Status status, ListenerCollector listenerCollector, CoordinateSystem coordinateSystem, AccessNodeList accessNodeList) {
        super(status, listenerCollector, coordinateSystem);
        this.accessNodeList = accessNodeList;
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
        FantasyEvent event = new FantasyEvent(this, "AccessListener", description);
        notifyListeners(event);
    }

    @Override
    public synchronized void allowNetworkAccess(int port) throws IOException {
        if (userConnectServer == null) {
            userConnectServer = new SimplePlatServer(port, this, this.getClass().getSimpleName());
            userConnectServer.setClassType(this.getClass().getSimpleName());
            setState("NETWORK", StateConstant.NETWORK_READY);
        }
    }

    @Override
    public boolean isNetWorkAccessibleReady(int port) {
        if (userConnectServer != null
                && userConnectServer.getServerSocket() != null
                && userConnectServer.getServerSocket().getLocalPort() == userConnectServer.getPort()
                && userConnectServer.getServerSocket().getLocalPort() == port) {
            return getCurrentStateCode("NETWORK") == StateConstant.NETWORK_READY;
        }

        return false;
    }

    @Override
    public boolean openConnection() {
        new Thread(userConnectServer).start();
        return true;

    }
}
