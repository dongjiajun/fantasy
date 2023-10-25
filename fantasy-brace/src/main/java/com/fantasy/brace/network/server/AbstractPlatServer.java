package com.fantasy.brace.network.server;


import com.fantasy.brace.graphic.plat.AbstractNetWorkAccessiblePlat;

import java.io.IOException;

/**
 * 抽象的可网络连接的图服务器
 *
 * @author DongJiaJun
 */
public abstract class AbstractPlatServer extends AbstractServer {

    /**
     * 可联网的地图
     */
    private AbstractNetWorkAccessiblePlat netWorkAccessiblePlat;

    public AbstractPlatServer() {
        netWorkAccessiblePlat = null;
    }

    public AbstractPlatServer(AbstractNetWorkAccessiblePlat netWorkAccessiblePlat) {
        this.netWorkAccessiblePlat = netWorkAccessiblePlat;
    }

    public AbstractPlatServer(int port, AbstractNetWorkAccessiblePlat netWorkAccessiblePlat) throws IOException {
        super(port);
        this.netWorkAccessiblePlat = netWorkAccessiblePlat;
    }

    public AbstractNetWorkAccessiblePlat getNetWorkAccessiblePlat() {
        return netWorkAccessiblePlat;
    }

    protected void setNetWorkAccessiblePlat(AbstractNetWorkAccessiblePlat netWorkAccessiblePlat) {
        this.netWorkAccessiblePlat = netWorkAccessiblePlat;
    }
}
