package com.transportclock;

import java.util.Vector;

/**
 * Created by snake on 10.12.13.
 */
public  class TransportManager {
    Vector<TransportRoute> transportList = new Vector<TransportRoute>();
    public void addRoute(TransportRoute route)
    {
        transportList.add(route);

    }

}
