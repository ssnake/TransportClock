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

    public Float getDistnace(int index1, int index2)
    {
        Float ret = 0.0f;
        RoutePoint prev_p = null;

        for(int i = index1; i< index2; i++)
        {
            RoutePoint p = this.get(i);
            if (prev_p != null)
                ret += TransportMath.calcDist(p.getLat(), p.getLng(), prev_p.getLat(), prev_p.getLng());
            prev_p = p;

        }
        return ret;
    }
    public Float getDistance(RoutePoint p1, RoutePoint p2)
    {
        return 0.0f;
    }

    public Float getLength() {
        if (this.size() != 0)
            return getDistnace(0, this.size() - 1);
        else
            return 0.0f;

    }
}
