package com.snake.mapviewproxy;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import org.osmdroid.api.IMapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.safecanvas.ISafeCanvas;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Created by snake on 25.12.13.
 */
public class MapViewMarksOverlayProxyOSM extends MapViewMarksOverlayProxy {
    protected MapView mMapView;
    protected List<Overlay> mListOverlay;
    MarksOverlay marksOverlay;
    OnMarkClickListiner listiner;
    HashMap<MapViewOverlayItemProxy, OverlayItem> mMapProxyToOverlayItemList;

    public MapViewMarksOverlayProxyOSM(Drawable drawable, MapView mapView) {
        this.mMapView = mapView;
        this.mListOverlay = mapView.getOverlays();
        this.marksOverlay = new MarksOverlay(drawable, mapView, this);
        mMapProxyToOverlayItemList = new HashMap<MapViewOverlayItemProxy, OverlayItem>();
        mListOverlay.add(marksOverlay);


    }

    @Override
    public MapViewOverlayItemProxy addItem(GeoPoint point, String title, String desc) {
        OverlayItem item = new OverlayItem(title, desc, point);
        MapViewOverlayItemProxy ret = new MapViewOverlayItemProxyOSM(item);
        mMapProxyToOverlayItemList.put(ret, item);
        marksOverlay.addItem(item, ret);

        return ret;


    }
    @Override
    public void removeItem(MapViewOverlayItemProxy item) {
        OverlayItem i = mMapProxyToOverlayItemList.get(item);
        if (i != null) {
            marksOverlay.removeItem(i, item);
            mMapProxyToOverlayItemList.remove(item);
        }

    }
    @Override
    public void setOnMarkClickListiner(OnMarkClickListiner listiner) {
        this.listiner = listiner;
    }

    @Override
    public void free() {
        mListOverlay.remove(marksOverlay);

    }

    public class MarksOverlay extends ItemizedOverlay<OverlayItem> {
        private Vector<OverlayItem> itemList = new Vector<OverlayItem>();
        private Vector<MapViewOverlayItemProxy> proxyItemList = new Vector<MapViewOverlayItemProxy>();
        private MapView mapView;
        private MapViewMarksOverlayProxyOSM overlayProxy;



        // constructor
        public MarksOverlay(Drawable drawable, MapView mapView, MapViewMarksOverlayProxyOSM overlayProxy){
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
        public void removeItem(OverlayItem item, MapViewOverlayItemProxy proxyItem) {

            itemList.remove(item);
            proxyItemList.remove(proxyItem);
        }
        public void addItem(OverlayItem item, MapViewOverlayItemProxy proxyItem)
        {


            itemList.add(item);
            proxyItemList.add(proxyItem);

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
            MapViewOverlayItemProxy proxyItem = proxyItemList.get(index);

            overlayProxy.listiner.onClick(proxyItem);
            return true;
        }

        @Override
        public boolean onSnapToItem(int i, int i2, Point point, IMapView iMapView) {
            return false;
        }
    }
}
