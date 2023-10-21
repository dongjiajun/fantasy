package com.fantasy.brace.network;

/**
 * 实现此接口的类需要实现网络连接规范，且允许网络接入
 *
 * @author DongJiaJun
 */
public interface NetWorkAccessible {

    /**
     * 网络接入状态是否就绪
     *
     * @return 表示网络接入状态是否就绪的布尔值
     */
    boolean isNetWorkAccessibleReady();

    /**
     * 允许网络接入
     *
     * @param port 端口号
     * @return 表示是否允许网络接入的布尔值
     * @throws Exception 抛出的异常类型，在未自定义异常体系前，暂定为顶级异常类型
     */
    boolean allowNetworkAccess(int port) throws Exception;
}

