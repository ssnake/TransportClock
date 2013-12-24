package com.transportclock;

import java.io.StringWriter;
import java.util.Vector;
import org.json.JSONWriter;

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

        for(int i = index1; i<= index2; i++)
        {
            RoutePoint p = this.get(i);
            if (prev_p != null)
                ret += TransportMath.calcDist(p.getLat(), p.getLng(), prev_p.getLat(), prev_p.getLng());
            prev_p = p;

        }
        return ret;
    }
    Float calcDist(RoutePoint p1, RoutePoint p2)
    {
        return TransportMath.calcDist(p1.getLat(), p1.getLng(), p2.getLat(), p2.getLng());

    }
    RoutePoint findNearestBusStop(RoutePoint source_p)
    {
        Float dist = Float.MAX_VALUE;
        RoutePoint ret_p = null;
        for(RoutePoint p: this)
        {

            if (p.isBusStop() && calcDist(p, source_p) < dist)
            {
                ret_p = p ;
                dist = calcDist(p, source_p);
            }

        }
        return ret_p;
    }
    public Float getDistance(RoutePoint p1, RoutePoint p2)
    {
        //find nearest BusStop;

        RoutePoint np1 = findNearestBusStop(p1);
        RoutePoint np2 = findNearestBusStop(p2);
        int i1 = this.indexOf(np1);
        int i2 = this.indexOf(np2);
        return getDistnace(i1, i2);
    }

    public Float getLength() {
        if (this.size() != 0)
            return getDistnace(0, this.size() - 1);
        else
            return 0.0f;

    }

    public String toJSON() {
        StringWriter sw = new StringWriter();

        JSONWriter ar = new JSONWriter(sw).array();
        for(RoutePoint p: this)
        {
            ar.value(p.toJSON());
        }
        ar.endArray();

        return sw.toString() ;
    }
}
