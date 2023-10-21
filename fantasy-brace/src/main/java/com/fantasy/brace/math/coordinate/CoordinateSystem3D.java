package com.fantasy.brace.math.coordinate;

import com.fantasy.brace.constant.CoordinateConstant;

public class CoordinateSystem3D extends CoordinateSystem2D {

    private Integer boundZ;

    private Integer ratioZ;


    public CoordinateSystem3D() {
        super();
        setBoundZ(10);
        setRatioZ(100);

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
        super(boundX, boundY, pixelsX, pixelsY);
        setBoundZ(boundZ);
        setRatioZ(pixelsZ);
    }

    @Override
    protected boolean validateCoordinateType(Coordinate coordinate) {
        return coordinate instanceof Coordinate3D;
    }

    @Override
    protected boolean validateCoordinate(Coordinate coordinate) {
        if (validateCoordinateType(coordinate)) {
            Coordinate3D real = (Coordinate3D) coordinate;
            return real.getX() >= 0
                    && real.getX() < getBoundX()
                    && real.getY() >= 0
                    && real.getY() < getBoundY()
                    && real.getZ() >= 0
                    && real.getZ() < getBoundZ();
        }
        return false;
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
        super.setBounds(axis);

        if(axis != null && axis.length >= 3){
            setBoundZ(axis[2]);

            return true;
        }

        return false;
    }

    /**
     * 从指定的字符串中解析得到符合规范的坐标
     *
     * @param coordinates 以字符串作为表现形式描述的坐标
     * @return 解析后生成的坐标值
     */
    public static Coordinate3D getCoordinates3DFrom(String coordinates) {
        if (coordinates != null) {
            String[] tmp = coordinates.trim().split(",");
            Coordinate3D res;

            if (tmp.length == CoordinateConstant.THREE) {
                try {
                    res = new Coordinate3D(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]));
                } catch (NumberFormatException nfe) {
                    return new Coordinate3D(-1, -1, -1);
                }
                return res;
            }
        }
        return new Coordinate3D(-1, -1, -1);
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
