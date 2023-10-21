package com.fantasy.brace.graphic.plat;


import com.fantasy.brace.common.rules.Processing;
import com.fantasy.brace.math.coordinate.CoordinateSystem;
import com.fantasy.brace.math.coordinate.CoordinateSystem3D;
import com.fantasy.brace.graphic.Graphic;

/**
 * 抽象的地图
 *
 * @author DongJiaJun
 */
public abstract class AbstractPlat extends Graphic {

    /**
     * 拥有一个三维坐标系
     */
    private CoordinateSystem coordinateSystem;

    /**
     * 无参构造，初始化坐标系，使用策略的方案
     */
    public AbstractPlat() {
        this.coordinateSystem = new CoordinateSystem3D();
    }

    /**
     * 获取坐标系实例
     *
     * @return 坐标系
     */
    public CoordinateSystem getCoordinateSystem() {
        return this.coordinateSystem;
    }

    /**
     * 初始化图
     *
     * @return 执行状态是否成功
     */
    abstract protected boolean initPlat();

    /**
     * 销毁图
     *
     * @return 执行状态是否成功
     */
    abstract protected boolean destroyPlat();
}
