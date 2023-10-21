package com.fantasy.core.executable.test;


import com.fantasy.brace.listener.event.FantasyEvent;
import com.fantasy.core.space.MainMap;

import java.io.IOException;

/**
 * 地图测试类
 *
 * @author DongJiaJun
 */
public class MapTest {

    public static void main(String[] args) throws IOException {
        MainMap om = MainMap.getInstance();

        om.setAccessListener((FantasyEvent eventObject) -> System.out.println(eventObject.getExtraStatement()));
        om.setStateListener((FantasyEvent eventObject) -> System.out.println(eventObject.getExtraStatement()));

        if (om.isNetWorkAccessibleReady()) {
            om.allowNetworkAccess(7001);
        }
    }
}
