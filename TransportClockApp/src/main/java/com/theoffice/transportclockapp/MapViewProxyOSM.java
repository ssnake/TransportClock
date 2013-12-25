package com.theoffice.transportclockapp;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IMapView;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;

import java.util.Vector;

/**
 * Created by snake on 25.12.13.
 */
public class MapViewProxyOSM extends MapViewProxy {
    MapView mapView;
    Context context;
    CompassOverlay compOverlay;
    Vector<MapViewOverlayProxy> overlayList = new Vector<MapViewOverlayProxy>();

    public MapViewProxyOSM(MapView mapView, Context context) {
        this.mapView = mapView;
        this.context = context;
        this.mapView.setUseSafeCanvas(true);
    }

    @Override
    public void setMultiTouchControls(Boolean enable) {
        mapView.setMultiTouchControls(enable);
    }

    @Override
    public void setBuiltInZoomControls(Boolean enable) {
        mapView.setBuiltInZoomControls(enable);
    }

    @Override
    public void showCompassOverlay(Boolean show) {

        if (compOverlay == null && show)
            compOverlay = new CompassOverlay(context, new InternalCompassOrientationProvider(context), mapView);
        if (show)
        {
            mapView.getOverlays().add(compOverlay);
            compOverlay.enableCompass();

        }
        else
        {
            compOverlay.disableCompass();
            mapView.getOverlays().remove(compOverlay);
            compOverlay = null;
        }

    }

    @Override
    public void setZoom(Integer value) {

        mapView.getController().setZoom(value);
    }

    @Override
    public MapViewOverlayProxy addOverlay(Integer drawable_resource_id) {
        Drawable drawable = context.getResources().getDrawable(drawable_resource_id);
        MapViewOverlayProxy ret = new MapViewOverlayProxyOSM(drawable, this.mapView);
        overlayList.add(ret);
        return ret;
    }
}
