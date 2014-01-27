package com.snake.mapviewproxy;

import android.graphics.PointF;
import android.graphics.drawable.Drawable;

/**
 * Created by snake on 15.01.14.
 */
public abstract class MapViewOverlayItemProxy {
    protected Object tagObject;

    public abstract PointF getPoint() ;

    public abstract void setDrawable(Drawable drawable);

    public abstract Drawable getDrawable() ;

    public abstract String getTitle();
    public abstract void setTitle();

    public abstract String toString();

    public void setTag(Object o) {
        tagObject = o;

    }
    public Object getTag() {
        return tagObject;
    }

}
