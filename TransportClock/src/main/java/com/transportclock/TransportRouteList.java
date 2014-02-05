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

    public static RoutePoint findNearestPoint(RoutePoint point, List<TransportRoute> list, int radiusM) {
        RoutePoint retPoint = null;
        Float retDist = Float.valueOf(radiusM);
        for(TransportRoute r: list) {

            RoutePoint nearestPoint = r.findNearestPoint(point);
            if (nearestPoint == null)
                continue;


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
