package com.fantasy.brace.math.coordinate;

import java.util.Objects;

/**
 * 三维坐标点
 */
public class CoordinatePoint3D extends CoordinatePoint2D {

    private Integer z;

    public CoordinatePoint3D(){
    };

    public CoordinatePoint3D(Integer x, Integer y, Integer z) {
        super(x, y);
        this.z = z;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Coordinate3D{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", z=" + getZ() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatePoint3D that = (CoordinatePoint3D) o;
        return Objects.equals(getX(), that.getX()) && Objects.equals(getY(), that.getY()) && Objects.equals(z, that.getZ());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }
}
