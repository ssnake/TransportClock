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
    TransportRoute addRoute()
    {
        //String json = readRawJSON(R.raw.route1);
        //return RouteGPSImporter.load(json, false);
        return null;


    }
    @Override
    public void loadAllRoutes(List<TransportRoute> routeList) {
        RouteGPSImporter.loadRoutes(readRawJSON(R.raw.all_routes), routeList);
        RouteGPSImporter.loadNames(readRawJSON(R.raw.route_names), routeList);
    }

}
