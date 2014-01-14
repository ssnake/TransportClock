package com.theoffice.transportclock.test;

import android.test.InstrumentationTestRunner;
import android.test.InstrumentationTestSuite;
import junit.framework.TestSuite;

/**
 * Created by snake on 13.01.14.
 */
public class Runner extends InstrumentationTestRunner {
    @Override
    public ClassLoader getLoader() {
        return Runner.class.getClassLoader();
    }

    @Override
    public TestSuite getAllTests() {
        InstrumentationTestSuite s = new InstrumentationTestSuite(this);
        //s.addTestSuite(com.theoffice.transportclock.test.SimpleTest.class);
        s.addTestSuite(com.theoffice.transportclock.test.MainActivityTest.class);
        return s;

    }
}
