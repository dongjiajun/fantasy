package com.fantasy.brace.listener;


import com.fantasy.brace.listener.listeners.AccessListener;

/**
 * 实现了此接口的类可以同时设置多个具有不同功能的用户连接状态监听器
 *
 * @author DongJiaJun
 */
public interface ConfigAccessListener extends ConfigListener {

    /**
     * 设置连接状态监听器
     *
     * @param listener 一个连接状态监听器
     */
    void setAccessListener(AccessListener listener);

    /**
     * 移除连接状态监听器
     *
     * @param listener 一个连接状态监听器
     */
    void removeAccessListener(AccessListener listener);


}
