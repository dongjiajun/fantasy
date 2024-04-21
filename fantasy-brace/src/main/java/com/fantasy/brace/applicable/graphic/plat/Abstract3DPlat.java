package com.fantasy.brace.applicable.graphic.plat;


import com.fantasy.brace.constant.StateConstant;
import com.fantasy.brace.math.coordinate.Coordinate;
import com.fantasy.brace.math.coordinate.CoordinateSystem3D;
import com.fantasy.brace.network.server.SimpleCoordinatePlatServer;

import java.io.IOException;

public abstract class Abstract3DPlat extends AbstractNetWorkAccessiblePlat implements Coordinate<CoordinateSystem3D> {

    /**
     * 拥有一个三维坐标系
     */
    private final CoordinateSystem3D coordinateSystem3D;


    protected Abstract3DPlat() {
        coordinateSystem3D = new CoordinateSystem3D();
    }

    protected Abstract3DPlat(CoordinateSystem3D coordinateSystem3D) {
        this.coordinateSystem3D = coordinateSystem3D;
    }

    @Override
    public CoordinateSystem3D getCoordinateSystem() {
        return coordinateSystem3D;
    }


    @Override
    public synchronized void allowNetworkAccess(int port) throws IOException {
        if (userConnectServer == null) {
            userConnectServer = new SimpleCoordinatePlatServer(port, this, this.getClass().getSimpleName(), this);
        }
        setState("NETWORK", StateConstant.NETWORK_READY);
    }
}
