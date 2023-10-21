package com.fantasy.brace.graphic;


import com.fantasy.brace.common.Status;
import com.fantasy.brace.common.datatype.ListenerCollector;
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
    private Status status;

    /**
     * 监听器容器，所有类型的监听器都将被收纳在此容器中
     */
    protected ListenerCollector listenerCollector;

    public Graphic() {
        status = new Status();
        listenerCollector = new ListenerCollector();
    }

    protected void setState(Integer state) {
        status.appendState(state);
        fireStateChanged();
    }

    protected void setState(String typeName, Integer code) {
        status.appendState(typeName, code);
        fireStateChanged();
    }

    protected int getState(String name) {
        return status.getState(name);
    }

    protected String getState() {
        return status.describe();
    }

    @Override
    public void setStateListener(StateListener listener) {
        HashSet stateListeners = this.listenerCollector.get("StateListener");
        if (stateListeners == null) {
            stateListeners = new HashSet<StateListener>();
            listenerCollector.put("StateListener", stateListeners);
        }
        stateListeners.add(listener);
    }

    @Override
    public void removeStateListener(StateListener listener) {
        HashSet mapStateListeners = this.listenerCollector.get("StateListener");
        if (mapStateListeners == null) {
            return;
        }
        listenerCollector.remove("StateListener");
    }

    /**
     * 响应状态改变，任何状态变动的行为都需要在执行后触发此方法
     */
    private void fireStateChanged() {
        if (listenerCollector == null) {
            return;
        }

        FantasyEvent event = new FantasyEvent(this, "StateListener", getState());
        notifyListeners(event);
    }

    /**
     * 移除所有监听器
     */
    protected void removeAllListeners() {
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
    protected void notifyListeners(FantasyEvent event) {
        HashSet<? extends FantasyListener> affectedListeners = listenerCollector.get(event.getType());

        if (affectedListeners == null) {
            return;
        }

        affectedListeners.forEach(listener -> listener.doEvent(event));
    }
}
