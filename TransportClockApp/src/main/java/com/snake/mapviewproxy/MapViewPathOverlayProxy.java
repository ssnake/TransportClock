package com.snake.mapviewproxy;

import org.osmdroid.util.GeoPoint;

/**
 * Created by snake on 26.12.13.
 */
public abstract class MapViewPathOverlayProxy extends MapViewOverlay {
    public abstract void addPoint(GeoPoint point);


}
