package com.theoffice.transportclockapp;

import android.content.SharedPreferences;
import com.transportclock.TransportConst;
import com.transportclock.TransportRoute;

import java.util.*;

/**
 * Created by snake on 12/29/13.
 */
public class UISettings {
    final String currentRouteIDName = "currentRouteID";
    final String timerEnabledName = "timerEnabled";

    HashMap<Integer, Boolean> routeVisiableList = new HashMap<Integer, Boolean>();
    Integer currentRouteID;
    Boolean timerEnabled = false;

    public void save(SharedPreferences pref) {
        pref.edit().putInt(currentRouteIDName, currentRouteID).commit();

    }
    public void load(SharedPreferences pref) {
        currentRouteID = pref.getInt(currentRouteIDName, TransportConst.NOID);
    }

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

    public Integer getCurrentRouteID() {
        return currentRouteID;
    }

    public void setCurrentRouteID(Integer currentRouteID) {
        this.currentRouteID = currentRouteID;
    }

    public Boolean getTimerEnabled() {
        return timerEnabled;
    }

    public void setTimerEnabled(Boolean timerEnabled) {
        this.timerEnabled = timerEnabled;
    }
}
