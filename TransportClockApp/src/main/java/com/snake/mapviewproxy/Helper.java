package com.snake.mapviewproxy;

import android.graphics.drawable.Drawable;

/**
 * Created by snake on 31.01.14.
 */
public class Helper {
    public static void boundCenterBottom(Drawable drawable)
    {
        drawable.setBounds(
                drawable.getIntrinsicWidth() / -2,
                - drawable.getIntrinsicHeight(),
                drawable.getIntrinsicWidth() / 2,
                0);
    }
    public static void boundCenter(Drawable drawable) {
        drawable.setBounds(
                drawable.getIntrinsicWidth()  / -2,
                drawable.getIntrinsicHeight() / -2,
                drawable.getIntrinsicWidth()  / 2,
                drawable.getIntrinsicHeight() / 2
        );
    }
}
