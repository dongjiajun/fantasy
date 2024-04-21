package com.fantasy.brace.math.coordinate;


public interface Coordinate<T extends CoordinateSystem<?>> {  //T extends CoordinateSystem<? extends AbstractCoordinatePoint>

    public T getCoordinateSystem();
}
