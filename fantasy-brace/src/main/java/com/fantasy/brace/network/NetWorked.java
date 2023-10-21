package com.fantasy.brace.network;

/**
 * 实现此接口的类需要实现网络接入规范，且可接入网络
 *
 * @author DongJiaJun
 */
public interface NetWorked {

    /**
     * 是否准备好了接入到其他网络
     *
     * @return 一个表示是否准备好接入到其他网络的布尔值
     */
    boolean isNetWorkedReady();

    /**
     * 连接到指定服务器
     *
     * @param host 主机
     * @param port 端口
     * @throws Exception 抛出的异常类型，在未自定义异常体系前，暂定为顶级异常类型
     */
    void connectTo(String host, int port) throws Exception;
}
