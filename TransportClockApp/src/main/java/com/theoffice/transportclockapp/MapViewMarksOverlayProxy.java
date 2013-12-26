package com.theoffice.transportclockapp;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.OverlayItem;

/**
 * Created by snake on 25.12.13.
 */
public abstract class MapViewMarksOverlayProxy {
    public interface OnMarkClickListiner {
        void onClick(OverlayItem item);
    }

    public abstract void addPoint(GeoPoint point, String title, String desc);
    public abstract void setOnMarkClickListiner(OnMarkClickListiner listiner);

    public class MapViewMarksOverlayProxyBasic extends MapViewMarksOverlayProxy {
        protected OnMarkClickListiner listiner;
        @Override
        public void setOnMarkClickListiner(OnMarkClickListiner listiner) {
            this.listiner = listiner;
        }

        @Override
        public void addPoint(GeoPoint point, String title, String desc) {

        }

    }
}
