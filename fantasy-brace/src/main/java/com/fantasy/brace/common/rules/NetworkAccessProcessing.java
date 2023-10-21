package com.fantasy.brace.common.rules;

import java.net.Socket;

/**
 * 实现此接口的类必须实现通用的连接处理规范
 *
 * @author DongJiaJun
 */
public interface NetworkAccessProcessing extends Processing {

    /**
     * 持有用户表示，添加到连接设备列表
     *
     * @param identity 以 “ip地址+":"+端口号” 组成的身份标识
     * @param socket   用户的连接
     * @return 表示添加是否成功的布尔值
     */
    boolean addToAccessNodeList(String identity, Socket socket);

    /**
     * 从连接列表中删除用户
     *
     * @param identity 以 “ip地址+":"+端口号” 组成的身份标识
     * @return 表示移除是否成功的布尔值
     */
    boolean removeFromAccessNodeList(String identity);

    /**
     * 如果用户存在，更新该用户信息
     *
     * @param identity 以 “ip地址+":"+端口号” 组成的身份标识
     * @param socket   用户的连接
     * @return 表示更新是否成功的布尔值
     */
    boolean updateWhenExistIn(String identity, Socket socket);

    // TODO: 2022/6/5 当前网络节点接入规范尚缺失关键组成，考虑需进行版本升级。

}
