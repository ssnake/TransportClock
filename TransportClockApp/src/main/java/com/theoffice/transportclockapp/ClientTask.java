package com.theoffice.transportclockapp;

import com.transportclock.TransportCar;
import com.transportclock.TransportClient;
import com.transportclock.TransportRoute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by snake on 14.01.14.
 */
public abstract class ClientTask {

    public abstract void run();
    public static ClientTask LoadAllRoutes(TransportClient client) {
        return new ClientTask.LoadAllRoutes(client);

    }
    public static ClientTask LoadRouteCars(TransportClient client,int route_id) {
        return new ClientTask.LoadRouteCars(client, route_id);
    }
    public static boolean GetTaskCarList(ClientTask task, List<TransportCar> carList) {
        boolean r = task instanceof LoadRouteCars;
        if (r) {
            carList.clear();
            carList.addAll(((LoadRouteCars) task).getCarList());
        }
        return r;
    }

    public static boolean GetTaskRouteList(ClientTask task, List<TransportRoute> routeList) {
        boolean r = task instanceof LoadAllRoutes;
        if (r) {
            routeList.clear();
            routeList.addAll( ((LoadAllRoutes) task).getRouteList());

        }
        return r;
    }
    public static class LoadRouteCars extends ClientTask {
        TransportClient mClient;
        int mRoute_ID;
        List<TransportCar> mCarList;

        public LoadRouteCars(TransportClient client, int route_id) {
            mCarList = new ArrayList<TransportCar>();
            mClient = client;
            mRoute_ID = route_id;
        }

        @Override
        public void run() {
            mClient.loadRouteCars(mRoute_ID, mCarList);

        }

        public List<TransportCar> getCarList() {
            return mCarList;
        }
    }

    public static class LoadAllRoutes extends ClientTask {
        TransportClient mClient;



        List<TransportRoute> mRouteList;
        public LoadAllRoutes(TransportClient client) {
            mClient = client;
            mRouteList = new ArrayList<TransportRoute>();
        }

        @Override
        public void run() {
            mClient.loadAllRoutes(mRouteList);

        }
        public List<TransportRoute> getRouteList() {
            return mRouteList;
        }
    }

}
