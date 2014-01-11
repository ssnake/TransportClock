package com.transportclock;

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
    final Map<String, String> params = new HashMap<String, String>();


    @Override
    public void loadAllRoutes(List<TransportRoute> routeList) {

    }

    @Override
    public void loadAllCars(int route_id, List<TransportCar> carList) {
        params.clear();
        params.put(actParam, getCarsParam);
        params.put(routeIDParam, ((Integer) route_id).toString());
        String url = baseURL + JSONURLReader.encodeParams(params);
        try {
            JSONObject jo = JSONURLReader.read(url);
            CarGPSImporter.load(jo, carList);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
