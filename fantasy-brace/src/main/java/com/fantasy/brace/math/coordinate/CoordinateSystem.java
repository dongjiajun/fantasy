package com.fantasy.brace.math.coordinate;

import java.util.Collection;

/**
 * 坐标系
 *
 * @author DJJ
 */
public interface CoordinateSystem {

    /**
     * Add a coordinate into this coordinate system.
     *
     * @param coordinate the coordinate
     * @return a Boolean which signed execute state of this method
     */
    boolean add(Coordinate coordinate);

    /**
     * Add a set of coordinates into this coordinate system.
     *
     * @param coordinates a set of coordinates
     * @return a Boolean which signed execute state of this method
     */
    boolean addAll(Collection<? extends Coordinate> coordinates);

    /**
     * Remove a specified coordinate from this coordinate system.
     *
     * @param coordinate the coordinate
     * @return a Boolean which signed execute state of this method
     */
    boolean remove(Coordinate coordinate);

    /**
     * Remove all coordinate from this coordinate system.
     */
    void removeAll();

    /**
     * Output a pieces of texts at the console that describes the state of the current coordinate system.
     */
    void describe();

    /**
     * set bounds of this CoordinateSystem.
     * @param axis length of axis
     * @return a Boolean which signed execute state of this method
     */
    boolean setBounds(Integer ...axis);
}
