package com.fantasy.brace.constant;

public class StateConstant {

    /**
     * 协议状态 - 网络 - 初始状态
     */
    public static Integer NETWORK_ORIGIN = 0b00;

    /**
     * 协议状态 - 网络 - 拒绝联网
     */
    public static Integer NETWORK_REJECT = 0b01;

    /**
     * 协议状态 - 网络 - 就绪
     */
    public static Integer NETWORK_READY = 0b11;

    /**
     * 协议状态 - 加载 - 初始状态
     */
    public static Integer LOAD_ORIGIN = 0b00;

    /**
     * 协议状态 - 加载 - 未完成
     */
    public static Integer LOAD_NO_COMPLETE = 0b01;

    /**
     * 协议状态 - 加载 - 已完成
     */
    public static Integer LOAD_COMPLETE = 0b11;
}
