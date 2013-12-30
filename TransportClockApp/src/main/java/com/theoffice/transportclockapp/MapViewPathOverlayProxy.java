package com.theoffice.transportclockapp;

import org.osmdroid.util.GeoPoint;

/**
 * Created by snake on 26.12.13.
 */
public abstract class MapViewPathOverlayProxy {
    public abstract void addPoint(GeoPoint point);
    public abstract void free();

}
