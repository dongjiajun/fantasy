package com.fantasy.core.space;


import com.fantasy.brace.constant.StateConstant;
import com.fantasy.brace.applicable.graphic.plat.AbstractNetWorkAccessiblePlat;
import com.fantasy.brace.listener.event.FantasyEvent;
import com.fantasy.brace.message.Message;
import com.fantasy.entity.constant.MapMaxBounds;

import java.util.Collection;
import java.util.HashMap;

/**
 * 主世界地图1
 *
 * @author DongJiaJun
 */
public class MainMap extends AbstractNetWorkAccessiblePlat {

    private static MainMap mainMap;

    /**
     * 地图的表示，只需要存储特殊的点即可(暂时使用这种结构存储，后续版本可考虑改为添加了一些自定义状态的set更加合适)
     */
    private HashMap<String, Message> map;


    private MainMap() {
        // 初始化资源的时候还没有注册监听器，先注册临时的监听器用于监听初始化和创建资源阶段的信息
        this.setStateListener((FantasyEvent eventObject) -> System.out.println(eventObject.getExtraStatement()));
        this.setAccessListener((FantasyEvent eventObject) -> System.out.println(eventObject.getExtraStatement()));


        if (initPlat()) {
            setState("LOAD", StateConstant.LOAD_COMPLETE);
            System.out.println("主世界地图MainMap已生成......");
        } else {
            System.out.println("主世界地图MainMap初始化失败，资源加载异常...");
            System.exit(1);
        }

        // 释放临时使用的所有监听器
        this.removeAllListeners();


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

        return false;
    }

    public void testStateChange() {
        setState("NETWORK", StateConstant.NETWORK_READY);
    }

    public void testBattle() {
        Collection<String> values = accessNodeList.keySet();
        for (String user : values) {
            System.out.println(user);
        }
    }

    public void enSureCoordinate() {

    }
}


