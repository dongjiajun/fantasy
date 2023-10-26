package com.fantasy.entity.constant;

/**
 * 边界定义类
 *
 * @author DongJiaJun
 */
public final class MapMaxBounds {
    /**
     * 极限边界
     */
    public static final long GLOBAL_LIMIT_BOUND = Long.SIZE == 64 ? (0x7fffffffffffffffL) : 0x7fffffff;

    /**
     * MainMap的坐标边界
     */
    public static final int MAX_M_X = Short.SIZE == 16 ? 0x00007fff : 0x0000007f;
    public static final int MAX_M_Y = Short.SIZE == 16 ? 0x00007fff : 0x0000007f;
    public static final int MAX_M_Z = Short.SIZE == 16 ? 0x00007fff : 0x0000007f;
    public static final int MIN_M_X = Short.SIZE == 16 ? 0xffff0000 : 0xffffff00;
    public static final int MIN_M_Y = Short.SIZE == 16 ? 0xffff0000 : 0xffffff00;
    public static final int MIN_M_Z = Short.SIZE == 16 ? 0xffff0000 : 0xffffff00;

    // TODO: 2022/6/24 自由区坐标限制  

}
