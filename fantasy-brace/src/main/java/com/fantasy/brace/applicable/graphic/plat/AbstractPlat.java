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
