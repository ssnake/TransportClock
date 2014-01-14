package com.theoffice.transportclock.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import com.theoffice.transportclockapp.MainActivity;

/**
 * Created by snake on 1/13/14.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityTest() {
        super(MainActivity.class);
    }

    @MediumTest
    public void testOne() {
        assertEquals(1, 1);
    }
}