package com.androidparty.partyapplication.utils;

/**
 * Created by 1 on 12/21/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Utils for checking network connection
 */
public class NetworkUtils {
    /**
     * @param context
     * @return is device connect to the internet
     */
    public static boolean isOnline(Context context) {
        final NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * @param context
     * @return information about network
     */
    private static NetworkInfo getNetworkInfo(Context context) {
        if (context == null)
            return null;
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    }
}
