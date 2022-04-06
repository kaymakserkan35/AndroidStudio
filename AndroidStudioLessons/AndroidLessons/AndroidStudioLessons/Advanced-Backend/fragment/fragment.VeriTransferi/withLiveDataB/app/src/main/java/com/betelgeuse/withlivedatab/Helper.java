package com.betelgeuse.withlivedatab;

import android.util.Log;

public  class Helper {
    public static void IsNull(Object obj) {
        if (obj==null) {
            Log.e("TAG", obj.getClass().getSimpleName() + "is null!!!");
        }
    }
}
