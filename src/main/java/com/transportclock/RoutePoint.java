package com.transportclock;

/**
 * Created by snake on 10.12.13.
 */
public class RoutePoint {
    Float lat;
    Float lng;

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

    @Override
    public boolean equals(Object obj) {
        RoutePoint p = (RoutePoint) obj;
        return lat.compareTo(p.getLat()) == 0 && lng.compareTo(p.getLng()) == 0;
    }
    public class StartPoint extends RoutePoint
    {

        public StartPoint(Float lat, Float lng) {
            super(lat, lng);
        }
    }
    public class FinishPoint extends RoutePoint {
        public FinishPoint(Float lat, Float lng) {
            super(lat, lng);
        }
    }

    ;
    public class StopPoint extends RoutePoint {
        public StopPoint(Float lat, Float lng) {
            super(lat, lng);
        }
    }

    ;

}
