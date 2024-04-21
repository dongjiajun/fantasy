package com.fantasy.brace.math.coordinate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 抽象坐标系
 *
 * @author DJJ
 */

public abstract class AbstractCoordinateSystem<T extends AbstractCoordinatePoint> implements CoordinateSystem<T> {

    /**
     * The coordinate collector of this coordinate system.
     */
    protected final List<T> collector;

    /**
     * No parameter constructor of the class, initialize the coordinate collector.
     */
    public AbstractCoordinateSystem() {
        collector = new ArrayList<>();
    }

    /**
     * verify that the current coordinates are legitimate coordinates.
     *
     * @param coordinate the coordinate
     * @return a Boolean which signed the conclusion
     */
    protected abstract boolean validateCoordinate(T t);

    @Override
    public boolean add(T t) {
        if (validateCoordinate(t)) {
            if (!collector.contains(t)) {
                return collector.add(t);
            }
        }
        return false;
    }

    @Override
    public void addAll(Collection<T> collection) {
        collection.forEach(this::add);
    }

    @Override
    public boolean remove(T t) {
        return collector.remove(t);
    }

    @Override
    public void removeAll() {
        collector.clear();
    }
}
