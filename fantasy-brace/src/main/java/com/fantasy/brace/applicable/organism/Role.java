package com.fantasy.brace.applicable.organism;

import com.fantasy.brace.math.coordinate.CoordinatePoint3D;
import com.fantasy.brace.network.NetWorked;
import com.fantasy.brace.network.client.SimpleClient;

/**
 * 角色类
 *
 * @author DongJiaJun
 */
public abstract class Role implements NetWorked {

    /**
     * 持有一个用于连接到服务器的客户端
     */
    protected SimpleClient simpleClient;

    /**
     * 名称
     */
    private String name;

    /**
     * 0x01 满状态,0x02 健康,0x03 濒死,0x04 死亡
     */
    private byte state;

    /**
     * 0x0001 人族，后续种族为动态添加，考虑建立映射，最多不超过2147483648*2-1个种族，留一个位置作为容错位
     */
    private int species;

    /**
     * 坐标
     */
    private CoordinatePoint3D coordinates;

    /**
     * 所在的地区（地图），同步数据到服务器时要使用
     */
    private String site;


    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setCoordinates(CoordinatePoint3D coordinates) {
        this.coordinates = coordinates;
    }

    public void setCoordinates(int x, int y, int z) {
        this.coordinates = new CoordinatePoint3D(x, y, z);
    }

    public CoordinatePoint3D getCoordinates() {
        return this.coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public int getSpecies() {
        return species;
    }

    public void setSpecies(int species) {
        setSpecies(null, species);
    }

    /**
     * 预留，obj是一个为了可能存在的由于后续过于庞大的种族查找而创建的一个查找生成器(因为在查找的时候可能还有一些别的逻辑,如特殊的种族可能需要创建一些内部类来赋予特殊属性)
     *
     * @param obj     目标
     * @param species 种族
     */
    public void setSpecies(Object obj, int species) {
        this.species = species;
    }

    @Override
    public boolean isNetWorkedReady() {
        return true;
    }

    @Override
    public void connectTo(String host, int port) {
        if (simpleClient == null) {
            simpleClient = new SimpleClient(host, port);
        }

        new Thread(simpleClient).start();
    }


}
