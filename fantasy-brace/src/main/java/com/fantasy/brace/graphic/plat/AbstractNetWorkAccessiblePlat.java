package com.fantasy.brace.graphic.plat;


import com.fantasy.brace.common.datatype.AccessNodeList;
import com.fantasy.brace.network.NetWorkAccessible;
import com.fantasy.brace.network.SimpleAccessiblePlatServer;

/**
 * 抽象的可网络连接的图
 *
 * @author DongJiaJun
 */
public abstract class AbstractNetWorkAccessiblePlat extends AbstractPlat implements NetWorkAccessible {
    /**
     * 接入到本地图的节点列表
     */
    protected AccessNodeList accessNodeList;

    /**
     * 管理连接的服务器(后续版本要改为nio的实现方式),在第二个版本改为用线程池实现的线程来管理我们的创建线程的逻辑
     */
    protected SimpleAccessiblePlatServer userConnectServer;

    /**
     * 无参构造，初始化连接节点列表
     */
    public AbstractNetWorkAccessiblePlat() {
        accessNodeList = new AccessNodeList();
    }


}
