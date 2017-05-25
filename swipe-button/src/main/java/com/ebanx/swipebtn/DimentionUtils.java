package com.ebanx.swipebtn;

import android.content.Context;
import android.util.TypedValue;

public class DimentionUtils {

    public static int convertSpToPixels(float sp, Context context) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        return px;
    }

    public static float converPixelsToSp(float px, Context context) {
        return px / context.getResources().getDisplayMetrics().scaledDensity;
    }
}
