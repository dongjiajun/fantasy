package com.fantasy.core.space;


import com.fantasy.brace.constant.StateConstant;
import com.fantasy.brace.listener.ConfigAccessListener;
import com.fantasy.brace.common.TmpTestRule;
import com.fantasy.brace.listener.listeners.AccessListener;
import com.fantasy.brace.listener.event.FantasyEvent;
import com.fantasy.brace.listener.listeners.FantasyListener;
import com.fantasy.brace.graphic.plat.AbstractNetWorkAccessiblePlat;
import com.fantasy.brace.message.Message;
import com.fantasy.brace.network.SimpleAccessiblePlatServer;

import java.io.IOException;
import java.util.Collection;
import java.util.EventListener;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 主世界地图1
 *
 * @author DongJiaJun
 */
public class MainMap extends AbstractNetWorkAccessiblePlat implements ConfigAccessListener, TmpTestRule {

    private static MainMap mainMap;

    /**
     * 地图的表示，只需要存储特殊的点即可(暂时使用这种结构存储，后续版本可考虑改为添加了一些自定义状态的set更加合适)
     */
    private HashMap<String, Message> map;


    private MainMap() {
        //即便都知道不显式调用也会默认调用父类无参构造，但显式注明可增加代码可读性
        super();
        setState("NETWORK", StateConstant.NETWORK_READY);
        if (initPlat()) {
            setState("LOAD", StateConstant.LOAD_COMPLETE);
            System.out.println("主世界地图MainMap已生成......");
        } else {

            // TODO: 2021/12/21 后续换成自定义的异常在上层捕获做处理

            System.out.println("主世界地图MainMap初始化失败，资源加载异常...");
            System.exit(1);
        }


    }

    /**
     * 采用线程安全的双校验懒汉式加载方式
     *
     * @return MainMap 实例
     */
    public static MainMap getInstance() {
        if (mainMap == null) {
            synchronized (MainMap.class) {
                if (mainMap == null) {
                    mainMap = new MainMap();
                }
            }
        }
        return mainMap;
    }


    @Override
    protected boolean initPlat() {
        //设置边界
        getCoordinateSystem().setBounds(MapMaxBounds.MAX_M_X, MapMaxBounds.MAX_M_Y, MapMaxBounds.MAX_M_Z);

        //初始化成员
        map = new HashMap<>();

        //用于测试的插入
        map.put("100,99,99", new Message("有未知事件被触发，请注意!"));

        return true;
    }

    @Override
    protected boolean destroyPlat() {

        // TODO: 2021/12/12 此处需要考虑的逻辑极其复杂，在一些相关的前置条件没有做好前，不做具体实现

        return false;
    }

    @Override
    public void setAccessListener(AccessListener listener) {
        HashSet accessListeners = this.listenerCollector.get("AccessListener");
        if (accessListeners == null) {
            accessListeners = new HashSet<AccessListener>();
            listenerCollector.put("AccessListener", accessListeners);
        }
        accessListeners.add(listener);
    }

    @Override
    public void removeAccessListener(AccessListener listener) {
        HashSet userConnectListeners = this.listenerCollector.get("AccessListener");
        if (userConnectListeners == null) {
            return;
        }
        listenerCollector.remove("AccessListener");
    }

    @Override
    public boolean allowNetworkAccess(int port) throws IOException {
        userConnectServer = new SimpleAccessiblePlatServer(accessNodeList, listenerCollector, port, this);
        userConnectServer.setClassType("MainMap");
        new Thread(userConnectServer).start();

        setState("NETWORK", StateConstant.NETWORK_READY);
        return true;
    }

    @Override
    public boolean isNetWorkAccessibleReady() {
        //资源初始化后，则网络连接状态就绪
        System.out.println(getState("NETWORK") == StateConstant.NETWORK_READY);
        return getState("NETWORK") == StateConstant.NETWORK_READY;
    }

    @Override
    public void testStateChange() {
        setState("NETWORK", StateConstant.NETWORK_READY);
    }

    @Override
    public void testBattle() {
        Collection<String> values = accessNodeList.keySet();
        for (String user : values) {
            System.out.println(user);
        }
    }

    @Override
    public void enSureCoordinate() {

    }
}


