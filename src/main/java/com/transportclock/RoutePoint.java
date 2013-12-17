package com.transportclock;

import org.json.JSONObject;
import org.json.JSONWriter;

import java.io.StringWriter;

/**
 * Created by snake on 10.12.13.
 */
public class RoutePoint {
    private static final String latField = "lat";
    private static final String lngField = "lng";
    public static final int  StartPoint = 1;
    public static final int  FinishPoint = 2;
    public static final int  BusStopPoint = 3;

    Float lat;
    Float lng;
    int type;

    public int getType() {
        return type;
    }


    public RoutePoint(Float lat, Float lng)
    {
        this(lat, lng, BusStopPoint);
    }
    public RoutePoint(Float lat, Float lng, int pointType)
    {
        this.type = pointType;
        this.lat = lat;
        this.lng = lng;
    }
    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object obj) {
        RoutePoint p = (RoutePoint) obj;
        return lat.compareTo(p.getLat()) == 0 && lng.compareTo(p.getLng()) == 0;
    }

    public String toJSON() {

        return new JSONObject(this).toString();
    }


    public boolean isBusStop() {
        return getType() == BusStopPoint;
    }
}
