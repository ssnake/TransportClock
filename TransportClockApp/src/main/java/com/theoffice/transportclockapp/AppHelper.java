package com.theoffice.transportclockapp;

import com.transportclock.TransportRoute;

/**
 * Created by snake on 16.01.14.
 */
public class AppHelper {

    public interface OnRouteSelectedListiner {
        public void onRouteSelected(TransportRoute route, boolean isVisiable);
        public TransportRoute getLastSelectedRoute();
    }
}
