package com.transportclock;

/**
 * Created by snake on 13.12.13.
 */
public class TransportMath {
    /* calc distance in meters */
    public static Float calcDist(Float lat1, Float lng1, Float lat2, Float lng2)
    {
        double earthRadius =6371000.8;
        //double earthRadius =  6378137.0;
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        //double sindLat = Math.sin(dLat / 2);
        //double sindLng = Math.sin(dLng / 2)  
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;

        return ((Double) dist).floatValue();
    }
    public static Float calcDist(RoutePoint p1, RoutePoint p2) {
        return calcDist(p1.getLat(), p1.getLng(), p2.getLat(), p2.getLng());
    }
}
