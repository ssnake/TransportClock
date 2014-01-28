package com.theoffice.transportclockapp;

import com.transportclock.TransportRoute;

import java.util.*;

/**
 * Created by snake on 12/29/13.
 */
public class UISettings {
    HashMap<Integer, Boolean> routeVisiableList = new HashMap<Integer, Boolean>();


    public  Boolean IsVisiable(TransportRoute route)
    {
        if (!routeVisiableList.containsKey(route.getId()))
            return Boolean.FALSE;
        else
            return routeVisiableList.get(route.getId());

    }
    public  void setVisiable(TransportRoute route, Boolean visiable){
        routeVisiableList.put(route.getId(), visiable);

    }

}
