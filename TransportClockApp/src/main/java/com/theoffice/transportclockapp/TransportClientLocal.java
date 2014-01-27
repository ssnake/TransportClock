package com.theoffice.transportclockapp;

import android.content.Context;
import com.transportclock.*;

import java.io.*;
import java.util.List;

/**
 * Created by snake on 12/29/13.
 */
public class TransportClientLocal extends TransportClientSumy {
    Context mContext;
    public TransportClientLocal(Context context) {
        mContext = context;

    }

    String readRawJSON(int resource_id)
    {
        InputStream is = mContext.getResources().openRawResource(resource_id);
        Writer w = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader r = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while((n= r.read(buffer)) != -1)
            {
                w.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return w.toString();

    }

    @Override
    public void loadRouteNames(List<TransportRoute> routeList) {
        RouteGPSImporter.loadRouteNames(readRawJSON(R.raw.route_names), routeList);
    }

}
