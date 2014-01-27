package com.transportclock;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by snake on 1/11/14.
 */
public class TransportClientSumy extends TransportClient {
    final String routeIDParam = "id";
    final String actParam = "act";
    final String getCarsParam = "cars";
    final String baseURL = "http://gps.meria.sumy.ua/mash.php?";
    final String getRoutePathParam = "path";
    final String getRouteDetailsParam = "marw";
    final String routeMarParam = "mar";
    final Map<String, String> params = new HashMap<String, String>();

    private String getUrl() {
        return baseURL + JSONURLReader.encodeParams(params);

    }
    @Override
    public void loadRouteNames(List<TransportRoute> routeList) {

    }

    @Override
    public void loadRouteCars(int route_id, List<TransportCar> carList) {
        params.clear();
        params.put(actParam, getCarsParam);
        params.put(routeIDParam, ((Integer) route_id).toString());

        try {
            JSONObject jo = JSONURLReader.read2JO(getUrl());
            CarGPSImporter.load(jo, carList);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void loadRoutePath(TransportRoute route) {
        params.clear();
        params.put(actParam, getRoutePathParam);
        params.put(routeIDParam, String.valueOf(route.getId()));
        params.put(routeMarParam, RouteGPSImporter.getID_Route(route));

        try {
            JSONArray ja = JSONURLReader.read2JA(getUrl());
            RouteGPSImporter.loadRoutePoints(ja, route);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadRouteDetails(TransportRoute route) {
        params.clear();
        params.put(actParam, getRouteDetailsParam);
        try {
            JSONArray ja = JSONURLReader.read2JA(getUrl());
            RouteGPSImporter.loadRouteDetails(ja, route);


        } catch (Exception e) {

        }
    }
}
