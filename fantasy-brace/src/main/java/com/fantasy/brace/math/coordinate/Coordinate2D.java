package com.fantasy.brace.math.coordinate;

import java.util.Objects;

/**
 * 二维坐标点
 *
 * @author DJJ
 */
public class Coordinate2D extends Coordinate {
    /**
     * 二维中的 x 坐标
     */
    private Integer x;

    /**
     * 二维中的 y 坐标
     */
    private Integer y;


    public Coordinate2D(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "x:" + getX() + "\t" + "y:" + getY() + "\n";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate2D that = (Coordinate2D) o;
        return x.equals(that.x) && y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
