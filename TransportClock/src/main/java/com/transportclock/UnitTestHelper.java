package com.transportclock;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Created by snake on 11.01.14.
 */
public class UnitTestHelper {
    public static String resource2String(Object obj, String res_name) throws Exception {


        InputStream is = obj.getClass().getResourceAsStream(res_name);
        //InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(res_name);
        if (is == null) {
            throw new Exception("Unable to find " + res_name);
        }
        StringWriter w = new StringWriter();
        String ret = "";
        try {
            IOUtils.copy(is, w);
            ret = w.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
