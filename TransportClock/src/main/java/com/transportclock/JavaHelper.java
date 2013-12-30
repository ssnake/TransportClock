package com.transportclock;

import java.util.List;

/**
 * Created by snake on 30.12.13.
 */
public  class JavaHelper {
    public static boolean listContainsInt(final List<Integer> array, final int key) {
        for (final int i : array) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }
}
