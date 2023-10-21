package com.fantasy.brace.listener.listeners;

import com.fantasy.brace.listener.event.FantasyEvent;

import java.util.EventListener;

/**
 * 此应用程序中的顶级监听器
 *
 * @author DongJiaJun
 */
public interface FantasyListener extends EventListener {

    /**
     * 基于监听的事件，监听器想要做的工作
     *
     * @param eventObject 事件对象
     */
    void doEvent(FantasyEvent eventObject);
}
