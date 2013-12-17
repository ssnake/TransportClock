package com.transportclock;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by snake on 17.12.13.
 */
public class RouteGPSImporter {
    static final String latField = "lat";
    static final String lngField = "lng";
    static final String directionField = "direction";
    public static RoutePoint loadRoutePoint(JSONObject jo)
    {
        return new RoutePoint(
                ((Double) jo.getDouble(latField)).floatValue(),
                ((Double) jo.getDouble(lngField)).floatValue());


    }
    public static TransportRoute load(String json, Boolean direction)
    {

        TransportRoute r = new TransportRoute();
        JSONArray ja = new JSONArray(json);
        for(int i=0; i<ja.length(); i++)
        {
            JSONObject jo = ja.getJSONObject(i);
            if (jo.getString(directionField).compareToIgnoreCase("t")==0 && direction ||
                jo.getString(directionField).compareToIgnoreCase("f")==0 && !direction )
                r.add(loadRoutePoint(jo));
        }
        return r;

    }
}
