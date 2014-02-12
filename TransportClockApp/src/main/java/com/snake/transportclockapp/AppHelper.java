package com.snake.transportclockapp;

import android.content.Context;
import com.transportclock.TransportRoute;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

    public static void copyRawFileToSD(Context context, int rawID, String path) throws IOException {
        InputStream in = context.getResources().openRawResource(rawID);
        FileOutputStream out = new FileOutputStream(path);
        byte[] buff = new byte[1024];
        int read = 0;

        try {
            while ((read = in.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
        } finally {
            in.close();

            out.close();
        }

    }
}
