package com.theoffice.transportclockapp;

import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import com.transportclock.JavaHelper;
import org.osmdroid.views.overlay.OverlayItem;

/**
 * Created by snake on 15.01.14.
 */
public class MapViewOverlayItemProxyOSM extends MapViewOverlayItemProxy {
    OverlayItem mOverlayItem;
    public MapViewOverlayItemProxyOSM(OverlayItem item) {
        mOverlayItem = item;
    }

    @Override
    public PointF getPoint() {

        return new PointF(
                JavaHelper.double2float(mOverlayItem.getPoint().getLatitude()),
                JavaHelper.double2float(mOverlayItem.getPoint().getLongitude()));
    }



    @Override
    public void setDrawable(Drawable drawable) {
        drawable.setBounds(
                0 - drawable.getIntrinsicWidth() / 2,
                0 - drawable.getIntrinsicHeight() ,
                drawable.getIntrinsicWidth() / 2,
                0);
        mOverlayItem.setMarker(drawable);


    }

    @Override
    public Drawable getDrawable() {
        return mOverlayItem.getMarker(0);
    }

    @Override
    public String getTitle() {
        return mOverlayItem.getTitle();
    }

    @Override
    public void setTitle() {


    }

    @Override
    public String toString() {
        return null;
    }
}
