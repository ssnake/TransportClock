package com.transportclock;

import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.CORBA.StringValueHelper;

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
    static final String numberField = "num";
    static final String nameField = "name";

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
            TransportRoute r = new TransportRoute();
            JSON2Route(jo, r);
            if (!JavaHelper.listContainsInt(retList, r.getId()))
                retList.add(r.getId());


        }
        return retList;



    }


    private static void JSON2Route(JSONObject jo,  TransportRoute route) {

        if (jo.has(idField))
            route.setId(jo.getInt(idField));
        if (jo.has(directionField))
            route.setDirection(jo.getString(directionField).compareToIgnoreCase("t")==0);
        if (jo.has(nameField))
            route.setName(jo.getString(nameField));
        if (jo.has(numberField))
            route.setNumber(jo.getString(numberField));




    }


    private static TransportRoute findByID_Route(String id_route, List<TransportRoute> routeList) {
        for (TransportRoute r : routeList) {
            if (!getID_Route(r).isEmpty() && getID_Route(r).compareToIgnoreCase(id_route) == 0)
                return r;
        }
        return null;
    }

    public static void loadRoutePoints(String json, TransportRoute route) {
        JSONArray ja = new JSONArray(json);
        loadRoutePoints(ja, route);

    }
    public static void loadRoutePoints(JSONArray array, TransportRoute route) {
        for (int i= 0; i< array.length(); i++) {
            route.add(JSON2Point(array.getJSONObject(i)));
        }
    }
    public static void loadRouteNames(String json, List<TransportRoute> routeList) {
        JSONArray ja = new JSONArray(json);
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);

            TransportRoute route = new TransportRoute();
            JSON2Route(jo, route);

            TransportRoute existed_route = TransportRouteList.findByID(route.getId(), routeList);
            TransportRoute targetRoute;
            if (existed_route != null) {
                targetRoute = existed_route;
            } else{
                targetRoute = route;
                routeList.add(targetRoute);
            }
            targetRoute.setName(route.getName());
            targetRoute.setNumber(route.getNumber());


        }


    }

    public static String getID_Route(TransportRoute route) {
        return route.getOther().getOrDefault(id_routeField, "");
    }


    public static void loadRouteDetails(JSONArray ja, TransportRoute route) {
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            route.addOtherValue(id_routeField, jo.optString(idField, "-1"));

        }

    }
}
