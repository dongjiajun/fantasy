package com.fantasy.brace.common;

/**
 * 临时测试规范，如在某些类中需要进行一些指定类型的测试，则可实现此接口
 *
 * @author DongJiaJun
 */
public interface TmpTestRule {

    /**
     * 测试状态改变
     */
    void testStateChange();

    /**
     * 测试战斗场景
     */
    void testBattle();

    /**
     * 确认坐标
     */
    void enSureCoordinate();
}
