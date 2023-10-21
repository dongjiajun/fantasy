package com.fantasy.brace.listener;


import com.fantasy.brace.listener.listeners.StateListener;

/**
 * 实现了此接口的类可以同时设置多个具有不同功能的状态改变监听器
 *
 * @author DongJiaJun
 */
public interface ConfigStateListener extends ConfigListener {

    /**
     * 设置状态改变监听器
     *
     * @param listener 一个状态监听器
     */
    void setStateListener(StateListener listener);

    /**
     * 移除状态改变监听器
     *
     * @param listener 一个状态监听器
     */
    void removeStateListener(StateListener listener);
}
