package com.transportclock;

/**
 * Created by snake on 10.12.13.
 */
public class RoutePoint {
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

    public boolean isBusStop() {
        return getType() == BusStopPoint;
    }
}
