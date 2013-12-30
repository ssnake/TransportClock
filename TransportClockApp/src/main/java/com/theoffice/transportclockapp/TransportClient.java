package com.theoffice.transportclockapp;

import com.transportclock.TransportRoute;

import java.util.List;

/**
 * Created by snake on 12/29/13.
 */
public abstract class TransportClient {
    public abstract void loadAllRoutes(List<TransportRoute> routeList);
}
