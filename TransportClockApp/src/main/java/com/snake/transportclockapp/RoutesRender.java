package com.snake.transportclockapp;

import android.graphics.Color;
import com.snake.mapviewproxy.MapViewPathOverlayProxy;
import com.snake.mapviewproxy.MapViewProxy;
import com.transportclock.RoutePoint;
import com.transportclock.TransportRoute;
import org.osmdroid.util.GeoPoint;

import java.util.HashMap;

/**
 * Created by snake on 12/29/13.
 *
 * RouteRender - class which show specified route on map if necessary
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
        switch(route.getId() % 8) {
            case 0: return Color.BLUE;
            case 1:
                return 0xFF008080;
            case 2: return Color.GREEN;
            case 3: return Color.MAGENTA;
            case 4:
                return 0xFF2c3342;
            case 5: return Color.RED;
            case 6:
                return 0xFF6a9c53;
            case 7:
                return 0xFFa81800;


        }
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

            mMapViewProxy.delOverlay(pathOverlay);
            mRouteOverlayMap.remove(route);

        }
        mMapViewProxy.refresh();
    }
}
