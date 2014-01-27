package com.snake.mapviewproxy;

import android.content.Context;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.PathOverlay;

import java.util.List;

/**
 * Created by snake on 26.12.13.
 */
public class MapViewPathOverlayProxyOSM extends MapViewPathOverlayProxy {

    List<Overlay> mListOverlay;
    PathOverlay mPathOverlay;


    public MapViewPathOverlayProxyOSM(Context context, List<Overlay> listOverlay, int default_color, int line_width) {
        this(context, listOverlay, default_color);
        mPathOverlay.getPaint().setStrokeWidth(line_width);
    }

        public MapViewPathOverlayProxyOSM(Context context, List<Overlay> listOverlay, int default_color) {
        this.mListOverlay = listOverlay;
        this.mPathOverlay = new PathOverlay(default_color, context);
        this.mListOverlay.add(mPathOverlay);

    }


    @Override
    public void addPoint(GeoPoint point) {
        mPathOverlay.addPoint(point);


    }

    @Override
    public void free() {
        mListOverlay.remove(mPathOverlay);
    }
}
