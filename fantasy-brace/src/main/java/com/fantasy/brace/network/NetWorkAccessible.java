package com.fantasy.brace.network;

/**
 * 可被网络接入的规范
 *
 * @author DongJiaJun
 */
public interface NetWorkAccessible {

    /**
     * 网络接入状态是否就绪
     *
     * @return 标识网络接入状态是否就绪的布尔值
     */
    boolean isNetWorkAccessibleReady(int port);

    /**
     * 允许网络接入
     *
     * @param port 端口号
     * @return 表示是否允许网络接入的布尔值
     * @throws Exception 抛出的异常类型，在未自定义异常体系前，暂定为顶级异常类型
     */
    void allowNetworkAccess(int port) throws Exception;

    /**
     * 启用连接
     * @return 标识执行是否成功的布尔值
     */
    boolean openConnection();
}

