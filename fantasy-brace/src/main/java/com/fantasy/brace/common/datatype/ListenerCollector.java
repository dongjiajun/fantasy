package com.fantasy.brace.common.datatype;

import com.fantasy.brace.listener.event.FantasyEvent;
import com.fantasy.brace.listener.listeners.FantasyListener;

import java.util.EventListener;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 自定义的监听器收集器
 *
 * @author DongJiaJun
 */
public class ListenerCollector extends HashMap<String, HashSet<? extends FantasyListener>> {
}
