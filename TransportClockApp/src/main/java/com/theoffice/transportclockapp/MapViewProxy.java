package com.theoffice.transportclockapp;

/**
 * Created by snake on 25.12.13.
 */
public abstract class MapViewProxy {
    public abstract void setMultiTouchControls(Boolean enable);
    public abstract void setBuiltInZoomControls(Boolean enable);
    public abstract void setZoom(Integer value);
    public abstract void showCompassOverlay(Boolean show);
    public abstract MapViewOverlayProxy addOverlay(Integer i);

}
