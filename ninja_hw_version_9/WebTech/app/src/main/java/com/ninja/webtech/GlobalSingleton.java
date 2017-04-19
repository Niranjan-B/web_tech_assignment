package com.ninja.webtech;

/**
 * Created by niranjanb on 18/04/17.
 */

public class GlobalSingleton {
    public static double getmLat() {
        return mLat;
    }

    public static void setmLat(double mLat) {
        GlobalSingleton.mLat = mLat;
    }

    public static double getmLon() {
        return mLon;
    }

    public static void setmLon(double mLon) {
        GlobalSingleton.mLon = mLon;
    }

    private static double mLat, mLon;
}
