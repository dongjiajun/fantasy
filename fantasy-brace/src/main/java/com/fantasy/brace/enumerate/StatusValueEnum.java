package com.fantasy.brace.enumerate;


/**
 * 列举的状态值枚举，需要配合状态协议位置枚举使用
 */
public enum StatusValueEnum {

    NETWORK_ORIGIN(0b00, "NETWORK", "网络状态未初始化"),
    NETWORK_REJECT(0b01, "NETWORK", "网络状态为拒绝联网"),
    NETWORK_READY(0b11, "NETWORK", "网络状态已就绪，允许联网"),
    LOAD_ORIGIN(0b00, "LOAD", "加载状态未初始化"),
    LOAD_NO_COMPLETE(0b01, "LOAD", "当前加载未完成"),
    LOAD_COMPLETE(0b11, "LOAD", "状态已加载完毕");


    /**
     * 状态值
     */
    private final int code;

    /**
     * 协议状态主题
     */
    private final String theme;

    /**
     * 当前主题下当前状态值对应的状态描述
     */
    private final String description;

    public int getCode() {
        return code;
    }

    public String getTheme() {
        return theme;
    }

    public String getDescription() {
        return description;
    }

    StatusValueEnum(int code, String theme, String description) {
        this.code = code;
        this.theme = theme;
        this.description = description;
    }

    /**
     * 依据主题和状态码定位唯一的配置项，如找不到对应的状态项则返回空
     *
     * @param code  状态值
     * @param theme 协议状态主题
     * @return 配置项
     */
    public static StatusValueEnum getItemByCode(int code, String theme) {
        for (StatusValueEnum value : StatusValueEnum.values()) {
            if (value.code == code && value.theme.equals(theme)) {
                return value;
            }
        }

        return null;
    }

    /**
     * 依据主题和状态码找到对应的状态描述，如找不到则返回空
     *
     * @param code  状态值
     * @param theme 协议状态主题
     * @return 状态描述
     */
    public static String getDescription(int code, String theme) {
        for (StatusValueEnum value : StatusValueEnum.values()) {
            if (value.code == code && value.theme.equals(theme)) {
                return value.description;
            }
        }

        return null;
    }
}
