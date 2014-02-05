package com.transportclock;

import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

/**
 * Created by snake on 30.12.13.
 */
public  class JavaHelper {
   public static boolean listContainsInt(final List<Integer> array, final int key) {
            return Collections.binarySearch(array, key) >= 0;

   }
    public static Float optFloat(String key, JSONObject jo) {
        return ((Double) jo.optDouble(key)).floatValue();
    }
    public static Float getAngle(RoutePoint p1, RoutePoint p2) {
       Double angle = Math.atan((p2.getLng() - p1.getLng()) / (p2.getLat() - p1.getLat()));
       angle = Math.atan2( p2.getLat() - p1.getLat(), p2.getLng() - p1.getLng());
       angle = Math.toDegrees(angle);
       if(angle < 0) {
            angle += 360;
       }
       return angle.floatValue();
    }
    public static float double2float(double d) {
        return ((Double) d).floatValue();
    }

}
