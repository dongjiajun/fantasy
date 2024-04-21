package com.fantasy.brace.math.coordinate;

import java.util.Collection;

/**
 * 坐标系
 *
 * @author DJJ
 */
public interface CoordinateSystem<T extends AbstractCoordinatePoint> {

    /**
     * Add a coordinate into this coordinate system.
     *
     * @param coordinate the coordinate
     * @return a Boolean which signed execute state of this method
     */
    boolean add(T t);

    /**
     * Add a set of coordinates into this coordinate system.
     *
     * @param coordinates a set of coordinates
     * @return a Boolean which signed execute state of this method
     */
    void addAll(Collection<T> collection);

    /**
     * Remove a specified coordinate from this coordinate system.
     *
     * @param coordinate the coordinate
     * @return a Boolean which signed execute state of this method
     */
    boolean remove(T t);

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
     *
     * @param axis length of axis
     * @return a Boolean which signed execute state of this method
     */
    boolean setBounds(Integer... axis);
}
