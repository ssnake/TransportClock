package com.theoffice.transportclockapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import com.snake.mapviewproxy.Helper;
import com.snake.mapviewproxy.MapViewMarksOverlayProxy;
import com.snake.mapviewproxy.MapViewOverlayItemProxy;
import com.snake.mapviewproxy.MapViewProxy;
import com.transportclock.TransportCar;
import org.osmdroid.util.GeoPoint;

import java.util.HashMap;
import java.util.List;

/**
 * Created by snake on 15.01.14.
 */
public class CarsRender implements MapViewMarksOverlayProxy.OnMarkClickListiner {

    MapViewProxy mMapViewProxy;
    MapViewMarksOverlayProxy mCarOverlay;
    HashMap<Integer, MapViewOverlayItemProxy> mCarMarkList;
    int mCarDrawableID = R.drawable.nav_right_red;

    int getCarID(TransportCar car) {
        return car.getRoute_id() % 100 * 100 + car.getId() % 100;
    }
    public CarsRender(MapViewProxy mapViewProxy) {

        mMapViewProxy = mapViewProxy;
        mCarMarkList = new HashMap<Integer, MapViewOverlayItemProxy>();


    }
    private void ensureOverlay() {
        if (mCarOverlay == null)
            mCarOverlay = mMapViewProxy.addMarksOverlay(mCarDrawableID);
        mCarOverlay.setOnMarkClickListiner(this);
    }
    private void showCar(TransportCar car) {
        if (!car.getAvaible())
            return;
        MapViewOverlayItemProxy item = mCarMarkList.get(getCarID(car));
        if (item == null) {
           item = mCarOverlay.addItem(
                   new GeoPoint(car.getLat(), car.getLng()),
                   "",
                   ""
           );
           mCarMarkList.put(getCarID(car), item);
        }
        Drawable d = rotateDrawable(car.getAngle());
        Helper.boundCenterBottom(d);
        item.setDrawable(d);

        item.setTag(car);



    }
    public Drawable rotateDrawable(float angle)
    {
        Bitmap arrowBitmap = BitmapFactory.decodeResource(mMapViewProxy.getContext().getResources(),
                mCarDrawableID);
        ;
        // Create blank bitmap of equal size
        Bitmap canvasBitmap = arrowBitmap.copy(Bitmap.Config.ARGB_8888, true);
        canvasBitmap.eraseColor(0x00000000);

        // Create canvas
        Canvas canvas = new Canvas(canvasBitmap);

        // Create rotation matrix
        Matrix rotateMatrix = new Matrix();
        rotateMatrix.setRotate(-angle, canvas.getWidth()/2, canvas.getHeight()/2);

        // Draw bitmap onto canvas using matrix
        canvas.drawBitmap(arrowBitmap, rotateMatrix, null);
        Drawable retDrawable =new BitmapDrawable(null, canvasBitmap);

        return  retDrawable;
    }
    public void clear() {
        mMapViewProxy.delOverlay(mCarOverlay);
        mCarMarkList.clear();
        mCarOverlay = null;

    }
    public void showCars(List<TransportCar> carList) {
        ensureOverlay();
        for (TransportCar car : carList)
            showCar(car);
        mMapViewProxy.refresh();
    }

    @Override
    public void onClick(MapViewOverlayItemProxy item) {
        TransportCar car = (TransportCar) item.getTag();
        Toast.makeText(mMapViewProxy.getContext(), car.toRaw(), Toast.LENGTH_SHORT).show();
    }
}
