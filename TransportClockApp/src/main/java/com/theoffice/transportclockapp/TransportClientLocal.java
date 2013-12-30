package com.theoffice.transportclockapp;

import android.content.Context;
import com.transportclock.RouteGPSImporter;
import com.transportclock.TransportRoute;

import java.io.*;
import java.util.List;

/**
 * Created by snake on 12/29/13.
 */
public class TransportClientLocal extends TransportClient {
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
        String json = readRawJSON(R.raw.route1);
        return RouteGPSImporter.load(json, false);


    }
    @Override
    public void loadAllRoutes(List<TransportRoute> routeList) {
        routeList.add(addRoute());
    }
}
