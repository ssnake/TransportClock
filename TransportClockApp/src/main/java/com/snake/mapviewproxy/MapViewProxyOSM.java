package com.snake.mapviewproxy;

import android.content.Context;
import android.graphics.drawable.Drawable;
import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.tileprovider.modules.MapTileFilesystemProvider;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;

import java.util.Vector;

/**
 * Created by snake on 25.12.13.
 */
public class MapViewProxyOSM extends MapViewProxy {
    MapView mMapView;
    Context mContext;
    CompassOverlay mCompOverlay;
    Vector<MapViewOverlay> mOverlayList = new Vector<MapViewOverlay>();

    BoundingBoxE6 mAreaLimit;

    public MapViewProxyOSM(MapView mapView, Context context) {
        this.mMapView = mapView;
        this.mContext = context;
        this.mMapView.setUseSafeCanvas(true);

    }

    @Override
    public void setMultiTouchControls(Boolean enable) {
        mMapView.setMultiTouchControls(enable);
    }

    @Override
    public void setCenter(GeoPoint point) {
        mMapView.getController().setCenter(point);


    }

    @Override
    public void delOverlay(MapViewOverlay overlay) {
        if (overlay != null)
            overlay.free();
        mOverlayList.remove(overlay);

    }

    @Override
    public void refresh() {
        mMapView.postInvalidate();

    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void setScrollableAreaLimit(BoundingBox area) {
        final Double margin = 0.1;
        mAreaLimit =
                new BoundingBoxE6(
                        area.getNorth()-margin,
                        area.getEast()+margin,
                        area.getSouth()+margin,
                        area.getWest()-margin
                );
        mMapView.setScrollableAreaLimit(mAreaLimit);

        mMapView.getController().animateTo(mAreaLimit.getCenter());

    }

    @Override
    public void setBuiltInZoomControls(Boolean enable) {
        mMapView.setBuiltInZoomControls(enable);
    }



    @Override
    public void showCompassOverlay(Boolean show) {

        if (mCompOverlay == null && show)
            mCompOverlay = new CompassOverlay(mContext, new InternalCompassOrientationProvider(mContext), mMapView);
        if (show)
        {
            mMapView.getOverlays().add(mCompOverlay);

            mCompOverlay.enableCompass();

        }
        else
        {
            mCompOverlay.disableCompass();
            mMapView.getOverlays().remove(mCompOverlay);
            mCompOverlay = null;
        }

    }

    @Override
    public MapViewPathOverlayProxy addPathOverlay(int default_color) {
        MapViewPathOverlayProxy pop = new MapViewPathOverlayProxyOSM(mContext, mMapView.getOverlays(), default_color, 5);
        mOverlayList.add(pop);
        return pop;
    }

    @Override
    public void setZoom(Integer value) {

        mMapView.getController().setZoom(value);
    }

    @Override
    public MapViewMarksOverlayProxy addMarksOverlay(Integer default_drawable_id) {
        Drawable drawable = mContext.getResources().getDrawable(default_drawable_id);
        MapViewMarksOverlayProxy ret = new MapViewMarksOverlayProxyOSM(drawable, this.mMapView);
        mOverlayList.add(ret);
        return ret;
    }
}
