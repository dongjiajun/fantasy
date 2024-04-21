package com.fantasy.brace.network.server;


import com.fantasy.brace.applicable.graphic.plat.AbstractNetWorkAccessiblePlat;
import com.fantasy.brace.applicable.graphic.plat.AbstractPlat;

import java.io.IOException;

/**
 * 抽象的可网络连接的图服务器
 *
 * @author DongJiaJun
 */
public abstract class AbstractPlatServer extends AbstractServer {

    /**
     * 地图
     */
    private AbstractPlat plat;


    protected AbstractPlatServer() {
        plat = null;
    }

    public AbstractPlatServer(int port, AbstractPlat plat) throws IOException {
        super(port);
        this.plat = plat;
    }

    protected AbstractPlat getPlat() {
        return plat;
    }

    protected void setPlat(AbstractPlat plat) {
        this.plat = plat;
    }
}
