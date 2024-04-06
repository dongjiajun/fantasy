package com.fantasy.brace.common;

import com.fantasy.brace.enumerate.StatusProtocolEnum;
import com.fantasy.brace.enumerate.StatusValueEnum;
import com.fantasy.brace.math.NumberConvertUtils;


/**
 * 一个可配置并自动管理的基于整型数字的状态机
 */
public class Status {

    /**
     * 状态容器
     */
    private Integer container;

    /**
     * 无参构造，创建状态实例并将状态置为初始状态
     */
    public Status() {
        this.container = 0;

    }

    /**
     * 有参构造，创建状态实例并将状态置为指定状态
     *
     * @param state 表示某种存在的情况的状态值
     */
    public Status(Integer state) {
        if (validateState(state)) {
            this.container = 0;
            appendState(state);
        }
    }

    /**
     * 在当前状态基础上追加状态
     *
     * @param state 用于追加的状态值，可以是一个复合状态
     * @return 执行状态
     */
    public void appendState(Integer state) {
        if (validateState(state)) {
            append(state);

        }
    }

    /**
     * 在当前状态基础上追加状态
     * @param type 协议状态名
     * @param code 对应的状态码值（单一）
     */
    public void appendState(String type, Integer code) {
        append(type, code);
    }

    /**
     * 执行复合状态的更新
     *
     * @param state 状态值
     */
    private void append(Integer state) {
        for (StatusProtocolEnum value : StatusProtocolEnum.values()) {
            int realCode = NumberConvertUtils.subIntValue(state, value.getHighOrder(), value.getLowOrder());
            append(value.name(), realCode);
        }
    }

    /**
     * 执行单一状态的更新
     *
     * @param type 协议状态名
     * @param code 状态码
     */
    public void append(String type, Integer code) {
        StatusProtocolEnum protocolEnum = StatusProtocolEnum.getItem(type);
        int rubber = 0;

        if (protocolEnum != null) {
            rubber = (int) Math.pow(2, protocolEnum.getHighOrder() - protocolEnum.getLowOrder() + 1) - 1;
            rubber = ~(rubber << protocolEnum.getLowOrder());

            if (StatusValueEnum.getItemByCode(code, type) != null) {
                container = container & rubber | (code << protocolEnum.getLowOrder());
            }
        }
    }

    /**
     * 根据协议状态名获取当前的状态值
     * @param name 协议状态名
     * @return 该协议状态当前的状态码值
     */
    public int getState(String name) {
        StatusProtocolEnum protocolEnum = StatusProtocolEnum.getItem(name);

        if (protocolEnum != null) {
            return NumberConvertUtils.subIntValue(container, protocolEnum.getHighOrder(), protocolEnum.getLowOrder());
        }

        return -1;
    }

    /**
     * 验证传入的状态机是否合法
     *
     * @param state 状态机
     * @return 判定结果
     */
    public static Boolean validateState(Integer state) {
        if (state != null) {
            return true;
        }
        return false;
    }

    /**
     * 验证当前状态中是否包含指定类型的状态
     *
     * @param name 状态中的分段协议名
     * @return 判定结果
     */
    public Boolean contain(String name) {
        StatusProtocolEnum protocolEnum = StatusProtocolEnum.getItem(name);

        if (protocolEnum != null) {
            return true;
        }

        return false;
    }

    /**
     * 依据当前状态机的数据，给出对当前完整状态的语言描述
     *
     * @return 一段描述当前状态的字符串
     */
    public String describe() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (StatusProtocolEnum protocolEnum : StatusProtocolEnum.values()) {
            int code = NumberConvertUtils.subIntValue(container, protocolEnum.getHighOrder(), protocolEnum.getLowOrder());
            String description = StatusValueEnum.getDescription(code, protocolEnum.name());
            if (description != null) {
                sb.append(description).append(", ");
            }
        }

        sb.delete(sb.length() - 2, sb.length());
        return sb.append("]").toString();
    }
}
