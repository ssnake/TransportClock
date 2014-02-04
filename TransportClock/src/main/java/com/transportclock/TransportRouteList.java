package com.transportclock;

import java.util.List;
import java.util.Vector;

/**
 * Created by snake on 30.12.13.
 */
public class TransportRouteList extends Vector<TransportRoute> {
    public static TransportRoute findByID(int id, List<TransportRoute> list)
    {
        for(TransportRoute r: list)
            if (r.getId() == id)
                return r;
        return null;

    }

    public static RoutePoint findNearestPoint(RoutePoint point, List<TransportRoute> list) {
        RoutePoint retPoint = null;
        Float retDist = Float.MAX_VALUE;
        for(TransportRoute r: list) {

            RoutePoint nearestPoint = r.findNearestPoint(point);
            if (nearestPoint == null)
                continue;
            if (retPoint == null)
                retPoint = nearestPoint;

            Float dist = TransportMath.calcDist(nearestPoint, point);
            if (dist < retDist)
            {
                retPoint = nearestPoint;
                retDist = dist;

            }

        }
        return retPoint;
    }
}
