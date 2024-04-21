package com.fantasy.brace.math.coordinate;

import com.fantasy.brace.constant.CoordinateConstant;


public class CoordinateSystem3D extends AbstractCoordinateSystem<CoordinatePoint3D> {

    /**
     * 三维坐标系 x 轴边界
     */
    private Integer boundX;

    /**
     * 三维坐标系 y 轴边界
     */
    private Integer boundY;

    /**
     * 三维坐标系 z 轴边界
     */
    private Integer boundZ;

    /**
     * x 坐标缩放比
     */
    private Integer ratioX;

    /**
     * y 坐标缩放比
     */
    private Integer ratioY;

    /**
     * z 坐标缩放比
     */
    private Integer ratioZ;


    public CoordinateSystem3D() {
        setBoundX(10);
        setBoundY(10);
        setBoundZ(10);
        setRatioX(100);
        setRatioY(100);
        setRatioZ(100);

    }

    public Integer getBoundX() {
        return boundX;
    }

    public void setBoundX(Integer boundX) {
        this.boundX = boundX;
    }

    public Integer getBoundY() {
        return boundY;
    }

    public void setBoundY(Integer boundY) {
        this.boundY = boundY;
    }

    public Integer getRatioX() {
        return ratioX;
    }

    public void setRatioX(Integer pixelsX) {
        this.ratioX = boundX != 0 ? pixelsX / boundX : 1;
    }

    public Integer getRatioY() {
        return ratioY;
    }

    public void setRatioY(Integer pixelsY) {
        this.ratioY = boundY != 0 ? pixelsY / boundY : 1;
    }

    public Integer getBoundZ() {
        return boundZ;
    }

    public void setBoundZ(Integer boundZ) {
        this.boundZ = boundZ;
    }

    public Integer getRatioZ() {
        return ratioZ;
    }

    public void setRatioZ(Integer pixelsZ) {
        this.ratioZ = boundZ != 0 ? pixelsZ / boundZ : 1;

    }

    public CoordinateSystem3D(Integer boundX, Integer boundY, Integer boundZ, Integer pixelsX, Integer pixelsY, Integer pixelsZ) {
        setBoundX(boundX);
        setBoundY(boundY);
        setRatioX(pixelsX);
        setRatioY(pixelsY);
        setBoundZ(boundZ);
        setRatioZ(pixelsZ);
    }

    @Override
    protected boolean validateCoordinate(CoordinatePoint3D coordinatePoint3D) {
        return coordinatePoint3D.getX() >= 0
                && coordinatePoint3D.getX() < getBoundX()
                && coordinatePoint3D.getY() >= 0
                && coordinatePoint3D.getY() < getBoundY()
                && coordinatePoint3D.getZ() >= 0
                && coordinatePoint3D.getZ() < getBoundZ();
    }

    @Override
    public void describe() {
        StringBuilder description = new StringBuilder();
        description.append("This is a CoordinateSystem3D\n")
                .append("bound_x:")
                .append(getBoundX())
                .append("\n")
                .append("bound_y:")
                .append(getBoundY())
                .append("\n")
                .append("bound_z:")
                .append(getBoundZ())
                .append("\n")
                .append("ratio_x:")
                .append(getRatioX())
                .append("\n")
                .append("ratio_y:")
                .append(getRatioY())
                .append("\n")
                .append("ratio_z:")
                .append(getRatioZ())
                .append("\n");
        description.append("current contain ").append(collector.size()).append(" Coordinate.\n");
        collector.forEach(description::append);
        System.out.println(description);
    }

    @Override
    public boolean setBounds(Integer... axis) {

        if (axis != null && axis.length == 3) {
            setBoundX(axis[0]);
            setBoundY(axis[1]);
            setBoundZ(axis[2]);
        }

        return false;
    }

    /**
     * 从指定的字符串中解析得到符合规范的坐标
     *
     * @param coordinates 以字符串作为表现形式描述的坐标
     * @return 解析后生成的坐标值
     */
    public static CoordinatePoint3D getCoordinates3DFrom(String coordinates) {
        if (coordinates != null) {
            String[] tmp = coordinates.trim().split(",");
            CoordinatePoint3D res;

            if (tmp.length == CoordinateConstant.THREE) {
                try {
                    res = new CoordinatePoint3D(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]));
                } catch (NumberFormatException nfe) {
                    return new CoordinatePoint3D(-1, -1, -1);
                }
                return res;
            }
        }
        return new CoordinatePoint3D(-1, -1, -1);
    }

    /**
     * 从指定的整型数组中得到某种符合规范的坐标
     *
     * @param coordinates 以整型数组作为表现形式描述的坐标
     * @return 解析后生成的坐标值
     */
    public static int[] getCoordinates3DFrom(int[] coordinates) {
        if (coordinates != null) {
            if (coordinates.length == CoordinateConstant.THREE) {
                return coordinates;
            }
        }
        return new int[]{-1, -1, -1};

    }
}
