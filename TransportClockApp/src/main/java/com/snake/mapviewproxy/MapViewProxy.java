package com.snake.mapviewproxy;

import android.content.Context;
import com.transportclock.TransportRoute;
import org.osmdroid.util.GeoPoint;

/**
 * Created by snake on 25.12.13.
 */
public abstract class MapViewProxy {
    public abstract void setMultiTouchControls(Boolean enable);
    public abstract void setBuiltInZoomControls(Boolean enable);
    public abstract void setZoom(Integer value);
    public abstract void showCompassOverlay(Boolean show);
    public abstract MapViewMarksOverlayProxy addMarksOverlay(Integer default_drawable_id);
    public abstract MapViewPathOverlayProxy addPathOverlay(int default_color);
    public abstract void setCenter(GeoPoint point);

    public abstract void delPathOverlay(MapViewPathOverlayProxy pathOverlay);

    public abstract void refresh();
    public abstract Context getContext();
    public abstract void setScrollableAreaLimit(BoundingBox area);

    public static class BoundingBox {
        Double north;
        Double west;
        Double south;
        Double east;
        public static BoundingBox MakeBoundingBox(TransportRoute route) {
            return new BoundingBox(route);
        }
        public BoundingBox(TransportRoute route) {

            north = ((Float) route.getMostNortLat()).doubleValue();
            west = ((Float) route.getMostWestLng()).doubleValue();
            south = ((Float) route.getMostSouthLat()).doubleValue();
            east = ((Float) route.getMostEastLng()).doubleValue();

        }

        public Double getNorth() {
            return north;
        }

        public void setNorth(Double north) {
            this.north = north;
        }

        public Double getWest() {
            return west;
        }

        public void setWest(Double west) {
            this.west = west;
        }

        public Double getSouth() {
            return south;
        }

        public void setSouth(Double south) {
            this.south = south;
        }

        public Double getEast() {
            return east;
        }

        public void setEast(Double east) {
            this.east = east;
        }
    }
}
