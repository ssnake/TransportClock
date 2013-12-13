package com.transportclock;

/**
 * Created by snake on 10.12.13.
 */
public class RoutePoint {
    public RoutePoint(Float lat, Float lng)
    {
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

    Float lat;
    Float lng;
    @Override
    public boolean equals(Object obj) {
        RoutePoint p = (RoutePoint) obj;
        return lat.compareTo(p.getLat()) == 0 && lng.compareTo(p.getLng()) == 0;
    }
}
