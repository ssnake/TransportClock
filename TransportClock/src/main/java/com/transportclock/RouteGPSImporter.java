package com.transportclock;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snake on 17.12.13.
 */
public class RouteGPSImporter {
    //yes lat=lng, gps tracker has issue, dunno why by fields lat and lgn are mixed
    static final String latField = "lng";
    static final String lngField = "lat";
    static final String directionField = "direction";
    static final String id_routeField = "id_route";
    static final String idField = "id";

    public static RoutePoint JSON2Point(JSONObject jo)
    {

        return new RoutePoint(
                ((Double) jo.getDouble(latField)).floatValue(),
                ((Double) jo.getDouble(lngField)).floatValue());


    }
    public static List<Integer> getAllRouteIDS(JSONArray ja)
    {
        ArrayList<Integer> retList = new ArrayList<Integer>();

        for(int i=0; i<ja.length(); i++)
        {
            JSONObject jo = ja.getJSONObject(i);
            TransportRoute r = JSON2Route(jo);
            if (!JavaHelper.listContainsInt(retList, r.getId()))
                retList.add(r.getId());


        }
        return retList;



    }


    private static TransportRoute JSON2Route(JSONObject jo) {
        TransportRoute route = new TransportRoute();
        int id = -1;
        Boolean dir = false;
        if (jo.has(idField))
            id = jo.getInt(idField);
        if (jo.has(directionField))
            dir = jo.getString(directionField).compareToIgnoreCase("t")==0;

        route.setId(id);
        route.setDirection(dir);


        return route;
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
                r.add(JSON2Point(jo));
        }
        return r;

    }

    public static void loadRoutes(String json, List<TransportRoute> routeList)
    {
        JSONArray ja = new JSONArray(json);

        for(int i=0; i<ja.length(); i++)
        {
            JSONObject jo = ja.getJSONObject(i);
            TransportRoute route = JSON2Route(jo);
            //check if it 's already present in list

            TransportRoute existed_route = TransportRouteList.findByID(route.getId(), routeList);
            if (existed_route != null)
                route = existed_route;
            else
               routeList.add(route);

            route.add(JSON2Point(jo));
        }

    }
}
