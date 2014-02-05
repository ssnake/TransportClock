package com.theoffice.transportclock.test;

import android.test.AndroidTestCase;
import com.theoffice.transportclockapp.AsyncClientTask;
import com.theoffice.transportclockapp.ClientTask;
import com.transportclock.TransportClient;
import com.transportclock.TransportClientSumy;

import java.util.concurrent.CountDownLatch;

/**
 * Created by snake on 1/13/14.
 */
public class SimpleTest extends AndroidTestCase implements AsyncClientTask.TaskUpdateListener{
    TransportClient mClient;
    CountDownLatch mLatch;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        mClient = new TransportClientSumy();

    }

    public void testOne() {
        assertEquals(1, 1);
    }


    @Override
    public void onTaskUpdate(ClientTask task) {
        mLatch.countDown();

    }
}
