package com.fantasy.brace.enumerate;

import java.util.Arrays;

/**
 * 状态协议位置定义枚举
 */
public enum StatusProtocolEnum {

    /**
     * 用一个整型类型变量作为状态收集器
     * x64架构下，标准的32位的状态位：0000 0000 0000 0000 0000 0000 0000 0000
     * <p>
     * 31，32位作为加载状态位：
     * 00 还未初始化
     * 01 就绪
     * 11 完成
     * <p>
     * 29，30位作为网络状态位:
     * 00 不允许接入网络
     * 01 已允许联网
     * 11 暂时不用，作为预备的扩充状态
     * <p>
     * 其他位暂未使用，预留
     */
    LOAD(0, 1),
    NETWORK(2, 3);


    /**
     * 占位低位下标（0为计数起始位）
     */
    private final int lowOrder;

    /**
     * 占位高位下标（当前状态机的高位限定值为 31）
     */
    private final int highOrder;

    StatusProtocolEnum(int lowOrder, int highOrder) {
        this.lowOrder = lowOrder;
        this.highOrder = highOrder;
    }

    public int getLowOrder() {
        return lowOrder;
    }

    public int getHighOrder() {
        return highOrder;
    }

    /**
     * 依据给定的下标确认协议状态，如未找到则返回空
     *
     * @param number 下标
     * @return 协议状态名称
     */
    public static String findNameBySite(int number) {
        for (StatusProtocolEnum value : StatusProtocolEnum.values()) {
            if (value.lowOrder <= number && value.highOrder >= number) {
                return value.name();
            }
        }

        return null;
    }

    /**
     * 确认给定的协议状态是否存在于当前的配置枚举中
     *
     * @param name 协议状态名
     * @return 判定结果
     */
    public static Boolean exist(String name) {
        return Arrays.stream(StatusProtocolEnum.values()).anyMatch(t -> t.name().equals(name));
    }

    /**
     * 依据协议状态名获取配置项
     *
     * @param name 协议状态名
     * @return 枚举项
     */
    public static StatusProtocolEnum getItem(String name) {
        if (exist(name)) {
            return StatusProtocolEnum.valueOf(name);
        }

        return null;
    }
}
