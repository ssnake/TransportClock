package com.transportclock;

import java.util.Vector;

/**
 * Created by snake on 30.12.13.
 */
public class TransportRouteList extends Vector<TransportRoute> {
    public TransportRoute findByID(int id)
    {
        for(TransportRoute r: this)
            if (r.getId() == id)
                return r;
        return null;

    }
}
