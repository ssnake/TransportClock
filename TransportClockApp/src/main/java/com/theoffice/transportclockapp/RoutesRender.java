package com.theoffice.transportclockapp;

import android.graphics.Color;
import com.transportclock.RoutePoint;
import com.transportclock.TransportRoute;
import org.osmdroid.util.GeoPoint;

import java.util.HashMap;

/**
 * Created by snake on 12/29/13.
 */
public class RoutesRender {
    MapViewProxy mMapViewProxy;
    HashMap<TransportRoute, MapViewPathOverlayProxy> mRouteOverlayMap;
    public RoutesRender(MapViewProxy mapViewProxy) {
        mMapViewProxy = mapViewProxy;
        mRouteOverlayMap = new HashMap<TransportRoute, MapViewPathOverlayProxy>();
    }
    int getRouteColor(TransportRoute route)
    {
        return Color.BLUE;

    }
    public void showRoute(TransportRoute route, Boolean visiable){
        MapViewPathOverlayProxy pathOverlay = mRouteOverlayMap.get(route);

        if (visiable)
        {

            if (pathOverlay == null)
            {
                pathOverlay = mMapViewProxy.addPathOverlay(getRouteColor(route));
                mRouteOverlayMap.put(route, pathOverlay);
                for(RoutePoint p: route)
                {
                    pathOverlay.addPoint(new GeoPoint(p.getLat(), p.getLng()));
                }

            }

        } else
        if (pathOverlay != null)
        {
            mMapViewProxy.delPathOverlay(pathOverlay);
            mRouteOverlayMap.remove(route);

        }
    }
}
