package com.fantasy.brace.listener.event;

import java.util.EventObject;

/**
 * fantasy中的顶级事件对象
 *
 * @author DongJiaJun
 */
public class FantasyEvent extends EventObject {
    /**
     * 额外的想要追加的一些提示信息
     */
    private final String extraStatement;

    /**
     * 监听器响应时的类型检测
     */
    private final String type;


    public FantasyEvent(Object source, String type, String extraStatement) {
        super(source);
        this.type = type;
        this.extraStatement = extraStatement;
    }

    /**
     * 获取检测的类型
     *
     * @return 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 获取额外追加的提示信息
     *
     * @return 追加的提示信息
     */
    public String getExtraStatement() {
        return "extraStatement:{[" + getSource().getClass().getSimpleName() + "]->" + getType() + "}=>\t" + extraStatement;
    }
}
