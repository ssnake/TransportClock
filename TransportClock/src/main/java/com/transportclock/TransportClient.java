package com.transportclock;

import java.util.List;

/**
 * Created by snake on 12/29/13.
 */
public abstract class TransportClient {
    public abstract void loadRouteNames(List<TransportRoute> routeList);

    public abstract void loadRouteCars(int route_id, List<TransportCar> carList);

    public abstract void loadRoutePath(TransportRoute route);
    public abstract void loadRouteDetails(TransportRoute route);
}
