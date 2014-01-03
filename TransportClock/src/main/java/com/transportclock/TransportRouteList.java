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
}
