package com.transportclock;

import java.util.Collections;
import java.util.List;

/**
 * Created by snake on 30.12.13.
 */
public  class JavaHelper {
   public static boolean listContainsInt(final List<Integer> array, final int key) {
            return Collections.binarySearch(array, key) >= 0;

   }

}
