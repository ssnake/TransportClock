package com.theoffice.transportclockapp;

import android.widget.Toast;
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

    int getCarID(TransportCar car) {
        return car.getRoute_id() % 100 * 100 + car.getId() % 100;
    }
    public CarsRender(MapViewProxy mapViewProxy) {

        mMapViewProxy = mapViewProxy;
        mCarMarkList = new HashMap<Integer, MapViewOverlayItemProxy>();
        mCarOverlay = mMapViewProxy.addMarksOverlay(R.drawable.ic_launcher);
        mCarOverlay.setOnMarkClickListiner(this);
    }
    private void showCar(TransportCar car) {
        MapViewOverlayItemProxy item = mCarMarkList.get(getCarID(car));
        if (item == null) {
           item = mCarOverlay.addItem(
                   new GeoPoint(car.getLat(), car.getLng()),
                   "",
                   ""
           );
           mCarMarkList.put(getCarID(car), item);
        }
        item.setTag(car);



    }
    public void clear() {
        for(MapViewOverlayItemProxy p: mCarMarkList.values()) {
            mCarOverlay.removeItem(p);
        }
        mCarMarkList.clear();

    }
    public void showCars(List<TransportCar> carList) {
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
