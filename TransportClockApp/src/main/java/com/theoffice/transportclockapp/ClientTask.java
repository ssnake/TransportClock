package com.theoffice.transportclockapp;

import com.transportclock.TransportCar;
import com.transportclock.TransportClient;
import com.transportclock.TransportRoute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snake on 14.01.14.
 */
public abstract class ClientTask {
    TransportClient mClient;

    protected ClientTask(TransportClient mClient) {
        this.mClient = mClient;
    }

    public abstract void run();
    public static ClientTask MakeLoadRouteNames(TransportClient client) {
        return new LoadRouteNames(client);

    }
    public static ClientTask MakeLoadRouteCars(TransportClient client, int route_id) {
        return new ClientTask.LoadRouteCars(client, route_id);
    }
    public static ClientTask MakeLoadRouteDetails(TransportClient client, TransportRoute route) {
        return new LoadRouteDetails(client, route);
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
        boolean r = task instanceof LoadRouteNames;
        if (r) {
            routeList.clear();
            routeList.addAll(((LoadRouteNames) task).getRouteList());

        }
        return r;
    }


    public static class LoadRouteCars extends ClientTask {

        int mRoute_ID;
        List<TransportCar> mCarList;

        public LoadRouteCars(TransportClient client, int route_id) {
            super(client);
            mCarList = new ArrayList<TransportCar>();

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

    public static class LoadRouteNames extends ClientTask {




        List<TransportRoute> mRouteList;
        public LoadRouteNames(TransportClient client) {
            super(client);
            mRouteList = new ArrayList<TransportRoute>();
        }

        @Override
        public void run() {
            mClient.loadRouteNames(mRouteList);

        }
        public List<TransportRoute> getRouteList() {
            return mRouteList;
        }
    }
    public static class LoadRouteDetails extends  ClientTask {
        TransportRoute mRoute;

        protected LoadRouteDetails(TransportClient mClient, TransportRoute route) {
            super(mClient);
            mRoute = route;
        }

        @Override
        public void run() {
            mClient.loadRouteDetails(mRoute);

        }
    }

}
