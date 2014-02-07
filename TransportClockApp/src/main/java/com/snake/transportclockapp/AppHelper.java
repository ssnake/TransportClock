package com.snake.transportclockapp;

import android.content.Context;
import com.transportclock.TransportRoute;

/**
 * Created by snake on 16.01.14.
 */
public class AppHelper {

    public interface OnRouteSelectedListiner {
        public void onRouteSelected(TransportRoute route, boolean isVisiable);
        public TransportRoute getLastSelectedRoute();
    }
    public static String getTaskName(Context context, ClientTask task) {
        String ret = "";
        if (task instanceof ClientTask.LoadRouteNames)
            ret = context.getString(R.string.routeNamesTask);
        if (task instanceof ClientTask.LoadRouteDetails)
            ret = context.getString(R.string.routeDetailsTask);
        if (task instanceof ClientTask.LoadRoutePath)
            ret = context.getString(R.string.routeRoutePath);
        return ret;
    }
}
