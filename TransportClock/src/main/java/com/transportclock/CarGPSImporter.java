package com.transportclock;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by snake on 11.01.14.
 */
public class CarGPSImporter {
    static final String latField = "X";
    static final String lngField = "Y";
    static final String idField = "CarId";
    static final String nameField = "CarName";
    static final String prevLatField = "pX";
    static final String prevLngField = "pY";
    static final String speedField = "SpeedV";
    static final String rowsField = "rows";
    public static TransportCar JSON2Car(JSONObject jo) {
        TransportCar car = new TransportCar();
        Float pLat = JavaHelper.optFloat(prevLatField, jo);
        Float pLng = JavaHelper.optFloat(prevLngField, jo);
        Float lat = JavaHelper.optFloat(latField, jo);
        Float lng = JavaHelper.optFloat(lngField, jo);
        Float angle = JavaHelper.getAngle(new RoutePoint(pLat, pLng), new RoutePoint(lat, lng));

        car.setId(jo.optInt(idField));
        car.setLat(lat);
        car.setLng(lng);
        car.setName(jo.optString(nameField));
        car.setSpeed(JavaHelper.optFloat(speedField, jo));
        car.setAngle(angle);


        return car;
    }
    public static void load(String json, List<TransportCar> carList) {
        JSONObject jo = new JSONObject(json);
        JSONArray ja = jo.optJSONArray(rowsField);
        for(int i=0; i<ja.length(); i++)
        {
            JSONObject joc = ja.getJSONObject(i);
            TransportCar car = JSON2Car(joc);
            carList.add(car);
        }
    }
}
