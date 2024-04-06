package com.fantasy.brace.applicable.graphic.plat;


import com.fantasy.brace.common.Status;
import com.fantasy.brace.datatype.ListenerCollector;
import com.fantasy.brace.math.coordinate.CoordinateSystem;
import com.fantasy.brace.math.coordinate.CoordinateSystem3D;
import com.fantasy.brace.applicable.graphic.Graphic;

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


    public AbstractPlat() {
        this.coordinateSystem = new CoordinateSystem3D();
    }

    public AbstractPlat(CoordinateSystem coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
    }

    public AbstractPlat(Status status, ListenerCollector listenerCollector, CoordinateSystem coordinateSystem) {
        super(status, listenerCollector);
        this.coordinateSystem = coordinateSystem;
    }

    public AbstractPlat(Status status, CoordinateSystem coordinateSystem) {
        super(status);
        this.coordinateSystem = coordinateSystem;
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
