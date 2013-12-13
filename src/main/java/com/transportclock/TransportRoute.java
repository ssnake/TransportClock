package com.transportclock;

import java.util.Vector;

/**
 * Created by snake on 10.12.13.
 * The class for Transport route
 */
public class TransportRoute extends Vector<RoutePoint>{

    public  RoutePoint add(Float lat, Float lng)
    {
        RoutePoint ret = new RoutePoint(lat, lng);
        this.add(ret);
        return ret;

    }


    public Float getLength() {
        Float ret = 0.0f;
        RoutePoint prev_p = null;
        for(RoutePoint p: this)
        {
            if (prev_p != null)
                ret += TransportMath.calcDist(p.getLat(), p.getLng(), prev_p.getLat(), prev_p.getLng());
            prev_p = p;

        }
        return ret;
    }
}
