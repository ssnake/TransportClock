package com.snake.mapviewproxy;

import org.osmdroid.util.GeoPoint;

/**
 * Created by snake on 25.12.13.
 */
public abstract class MapViewMarksOverlayProxy {
    public interface OnMarkClickListiner {
        void onClick(MapViewOverlayItemProxy item);
    }

    public abstract MapViewOverlayItemProxy addItem(GeoPoint point, String title, String desc);
    public abstract void removeItem(MapViewOverlayItemProxy item);
    public abstract void setOnMarkClickListiner(OnMarkClickListiner listiner);

}
