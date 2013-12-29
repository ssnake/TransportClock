package com.theoffice.transportclockapp;

import com.transportclock.TransportRoute;

import java.util.*;

/**
 * Created by snake on 12/29/13.
 */
public class UISettings {
    HashMap<TransportRoute, Boolean> routeVisiableList = new HashMap<TransportRoute, Boolean>();
    Observer observer;
    public UISettings(Observer observer) {
        this.observer = observer;
    }

    public  Boolean IsVisiable(TransportRoute route)
    {
        if (!routeVisiableList.containsKey(route))
            return Boolean.FALSE;
        else
            return routeVisiableList.get(route);

    }
    public  void setVisiable(TransportRoute route, Boolean visiable){
        if (IsVisiable(route) != visiable && observer != null)
        {
            observer.update(null, route);
        }
        routeVisiableList.put(route, visiable);

    }

}
