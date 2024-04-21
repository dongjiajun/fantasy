package com.fantasy.brace.math.coordinate;

/**
 * 二维坐标系
 *
 * @author DJJ
 */
public class CoordinateSystem2D extends AbstractCoordinateSystem<CoordinatePoint2D> {

    /**
     * 二维坐标系 x 轴边界
     */
    private Integer boundX;

    /**
     * 二维坐标系 y 轴边界
     */
    private Integer boundY;

    /**
     * x 坐标缩放比
     */
    private Integer ratioX;

    /**
     * y 坐标缩放比
     */
    private Integer ratioY;


    public CoordinateSystem2D() {
        setBoundX(10);
        setBoundY(10);
        setRatioX(100);
        setRatioY(100);
    }

    @Override
    protected boolean validateCoordinate(CoordinatePoint2D coordinatePoint2D) {
        return coordinatePoint2D.getX() >= 0 && coordinatePoint2D.getX() < boundX && coordinatePoint2D.getY() >= 0 && coordinatePoint2D.getY() < boundY;
    }

    public CoordinateSystem2D(Integer boundX, Integer boundY, Integer pixelsX, Integer pixelsY) {
        setBoundX(boundX);
        setBoundY(boundY);
        setRatioX(pixelsX);
        setRatioY(pixelsY);
    }

    @Override
    public void describe() {
        StringBuilder description = new StringBuilder();
        description.append("This is a CoordinateSystem2D\n")
                .append("bound_x:")
                .append(boundX)
                .append("\n")
                .append("bound_y:")
                .append(boundY)
                .append("\n")
                .append("ratio_x:")
                .append(ratioX)
                .append("\n")
                .append("ratio_y:")
                .append(ratioY)
                .append("\n")
                .append("real width:")
                .append(boundX * ratioX)
                .append("\n")
                .append("real height:")
                .append(boundY * ratioY)
                .append("\n");
        description.append("current contain ").append(collector.size()).append(" Coordinate.\n");
        collector.forEach(description::append);
        System.out.println(description);
    }

    @Override
    public boolean setBounds(Integer... axis) {
        if (axis != null && axis.length >= 2) {
            setBoundX(axis[0]);
            setBoundY(axis[1]);

            return true;
        }

        return false;
    }

    public Integer getBoundX() {
        return boundX;
    }

    public Integer getBoundY() {
        return boundY;
    }

    public Integer getRatioX() {
        return ratioX;
    }

    public Integer getRatioY() {
        return ratioY;
    }

    public void setBoundX(Integer boundX) {
        this.boundX = boundX;
    }

    public void setBoundY(Integer boundY) {
        this.boundY = boundY;
    }

    public void setRatioX(Integer pixelsX) {
        this.ratioX = boundX != 0 ? pixelsX / boundX : 1;
    }

    public void setRatioY(Integer pixelsY) {
        this.ratioY = boundY != 0 ? pixelsY / boundY : 1;
    }
}
