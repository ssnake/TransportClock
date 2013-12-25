package com.theoffice.transportclockapp;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import org.osmdroid.api.IMapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.List;
import java.util.Vector;

/**
 * Created by snake on 25.12.13.
 */
public class MapViewOverlayProxyOSM extends MapViewOverlayProxy {
    MapView mapView;
    List<Overlay> listOverlay;
    MarksOverlay marksOverlay;
    OnMarkClickListiner listiner;

    public MapViewOverlayProxyOSM(Drawable drawable, MapView mapView) {
        this.mapView = mapView;
        this.listOverlay = mapView.getOverlays();
        this.marksOverlay = new MarksOverlay(drawable, mapView, this);
        listOverlay.add(marksOverlay);


    }

    @Override
    public void addPoint(GeoPoint point, String title, String desc) {
        marksOverlay.addItem( new OverlayItem(title, desc, point));


    }
    @Override
    public void setOnMarkClickListiner(OnMarkClickListiner listiner) {
        this.listiner = listiner;
    }

    public class MarksOverlay extends ItemizedOverlay<OverlayItem> {
        private Vector<OverlayItem> itemList = new Vector<OverlayItem>();
        private MapView mapView;
        private MapViewOverlayProxyOSM overlayProxy;
        // constructor
        public MarksOverlay(Drawable drawable, MapView mapView, MapViewOverlayProxyOSM overlayProxy){
            super(drawable, mapView.getResourceProxy());
            this.mapView = mapView;
            this.overlayProxy = overlayProxy;
            boundCenterBottom(drawable);

        }
        void boundCenterBottom(Drawable drawable)
        {
            drawable.setBounds(
                    drawable.getIntrinsicWidth() / -2,
                    - drawable.getIntrinsicHeight(),
                    drawable.getIntrinsicWidth() / 2,
                    0);


        }
        public void addItem(OverlayItem item)
        {
            itemList.add(item);
            populate();
        }
        @Override
        protected OverlayItem createItem(int i) {
            return itemList.get(i);
        }

        @Override
        public int size() {
            return itemList.size();
        }


        @Override
        protected boolean onTap(int index) {
            OverlayItem item = itemList.get(index);
            overlayProxy.listiner.onClick(item);
            return true;
        }

        @Override
        public boolean onSnapToItem(int i, int i2, Point point, IMapView iMapView) {
            return false;
        }
    }
}
