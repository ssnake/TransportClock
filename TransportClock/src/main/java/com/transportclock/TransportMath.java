package com.transportclock;

/**
 * Created by snake on 13.12.13.
 */
public class TransportMath {
    public static Float calcDist(Float lat1, Float lng1, Float lat2, Float lng2)
    {
        double earthRadius =6371000.8;
        //double earthRadius =  6378137.0;
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;

        return ((Double) dist).floatValue();
    }
}
