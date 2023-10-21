package com.fantasy.brace.math.coordinate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 抽象坐标系
 *
 * @author DJJ
 */

public abstract class AbstractCoordinateSystem implements CoordinateSystem {

    /**
     * The coordinate collector of this coordinate system.
     */
    protected final List<Coordinate> collector;

    /**
     * No parameter constructor of the class, initialize the coordinate collector.
     */
    public AbstractCoordinateSystem() {
        collector = new ArrayList<>();
    }

    /**
     * Verify that the current coordinates match dimensions.
     *
     * @param coordinate the coordinate
     * @return a Boolean which signed the conclusion
     */
    protected abstract boolean validateCoordinateType(Coordinate coordinate);

    /**
     * verify that the current coordinates are legitimate coordinates.
     *
     * @param coordinate the coordinate
     * @return a Boolean which signed the conclusion
     */
    protected abstract boolean validateCoordinate(Coordinate coordinate);

    @Override
    public boolean add(Coordinate coordinate) {
        if (validateCoordinateType(coordinate)) {
            if (validateCoordinate(coordinate)) {
                if (!collector.contains(coordinate)) {
                    return collector.add(coordinate);
                }
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Coordinate> coordinates) {
        for (Coordinate coordinate : coordinates) {
            if (!add(coordinate)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean remove(Coordinate coordinate) {
        return collector.remove(coordinate);
    }

    @Override
    public void removeAll() {
        collector.clear();
    }

    @Override
    public void describe() {
        if (collector.size() > 0) {
            System.out.println("This is a coordinate system, contain these coordinate:");
            collector.forEach(System.out::print);
        } else {
            System.out.println("This is a coordinate system, contain none coordinate now.");
        }
    }
}
