package com.theoffice.transportclock.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import com.theoffice.transportclockapp.AsyncClientTask;
import com.theoffice.transportclockapp.ClientTask;
import com.theoffice.transportclockapp.MainActivity;
import com.theoffice.transportclockapp.TransportClientLocal;
import com.transportclock.TransportCar;
import com.transportclock.TransportClient;
import com.transportclock.TransportRoute;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by snake on 1/13/14.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> implements AsyncClientTask.TaskUpdateListener {
    MainActivity activity;
    TransportClient client;
    CountDownLatch latch;
    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        client = new TransportClientLocal(activity);

    }

    @MediumTest
    public void testOne() {
        assertEquals(1, 2);
    }
    public void testAsyncLoadAllRoutes() throws InterruptedException {
        latch = new CountDownLatch(1);
        List<TransportRoute> routeList = new ArrayList<TransportRoute>();
        ClientTask task = ClientTask.MakeLoadRouteNames(client);
        new AsyncClientTask(this).execute(task);
        latch.await();
        ClientTask.GetTaskRouteList(task, routeList);
        assertTrue(routeList.size() > 0);


    }

    public void testAsyncLoadRouteCars() throws InterruptedException {
        latch = new CountDownLatch(1);
        List<TransportCar> carList = new ArrayList<TransportCar>();
        ClientTask task = ClientTask.MakeLoadRouteCars(client, 0);
        new AsyncClientTask(this).execute(task);
        latch.await();
        ClientTask.GetTaskCarList(task, carList);
        assertTrue(carList.size() > 0);


    }
    @Override
    public void onTaskUpdate(ClientTask task) {
        latch.countDown();
    }
}