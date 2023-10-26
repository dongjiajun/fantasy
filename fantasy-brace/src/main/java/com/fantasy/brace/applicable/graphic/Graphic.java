package com.fantasy.brace.applicable.graphic;


import com.fantasy.brace.common.Status;
import com.fantasy.brace.datatype.ListenerCollector;
import com.fantasy.brace.listener.ConfigStateListener;
import com.fantasy.brace.listener.event.FantasyEvent;
import com.fantasy.brace.listener.listeners.FantasyListener;
import com.fantasy.brace.listener.listeners.StateListener;

import java.util.HashSet;

/**
 * 图形顶级类
 *
 * @author DongJiaJun
 */
public abstract class Graphic implements ConfigStateListener {

    /**
     * 状态收集器
     */
    private final Status status;

    /**
     * 监听器容器
     */
    private final ListenerCollector listenerCollector;


    public ListenerCollector getListenerCollector() {
        return listenerCollector;
    }

    public Graphic() {
        status = new Status();
        listenerCollector = new ListenerCollector();
    }

    public Graphic(Status status) {
        this.status = status;
        listenerCollector = new ListenerCollector();
    }

    public Graphic(Status status, ListenerCollector listenerCollector) {
        this.status = status;
        this.listenerCollector = listenerCollector;
    }

    /**
     * 设置全局状态
     *
     * @param state 一个全局状态
     */
    protected final void setState(Integer state) {
        status.appendState(state);
        fireStateChanged();
    }

    /**
     * 设置单一协议状态
     *
     * @param typeName 协议状态名
     * @param code     该协议状态的某一状态码值
     */
    protected final void setState(String typeName, Integer code) {
        status.appendState(typeName, code);
        fireStateChanged();
    }

    /**
     * 获取单一协议状态
     *
     * @param name 协议状态名
     * @return 该协议状态当前状态码值
     */
    protected int getCurrentStateCode(String name) {
        return status.getState(name);
    }

    /**
     * 获取全局状态描述
     *
     * @return
     */
    protected String getStateDescription() {
        return status.describe();
    }

    @Override
    public final void setStateListener(StateListener listener) {
        HashSet<FantasyListener> stateListeners = this.listenerCollector.get("StateListener");
        if (stateListeners == null) {
            stateListeners = new HashSet<>();
            listenerCollector.put("StateListener", stateListeners);
        }
        stateListeners.add(listener);
    }

    @Override
    public final void removeStateListener(StateListener listener) {
        HashSet<FantasyListener> mapStateListeners = this.listenerCollector.get("StateListener");
        if (mapStateListeners == null) {
            return;
        }
        listenerCollector.remove("StateListener");
    }

    /**
     * 响应状态改变
     */
    private void fireStateChanged() {
        FantasyEvent event = new FantasyEvent(this, "StateListener", getStateDescription());
        notifyListeners(event);
    }

    /**
     * 移除所有监听器
     */
    protected final void removeAllListeners() {
        if (listenerCollector == null || listenerCollector.isEmpty()) {
            return;
        }
        listenerCollector.clear();
    }

    /**
     * 监听器响应事件
     *
     * @param event 事件
     */
    protected final void notifyListeners(FantasyEvent event) {
        HashSet<FantasyListener> affectedListeners = listenerCollector.get(event.getType());
        if (affectedListeners == null) {
            return;
        }

        affectedListeners.forEach(listener -> listener.doEvent(event));
    }
}
