package com.hyrt.cnp.base.account.utils;

/**
 * Created by Zoe on 2014-05-12.
 */
public class LogHelper {
    public static void i(String TAG, String msg){
        android.util.Log.i(TAG, msg);
    }

    public static void d(String TAG, String msg){
        android.util.Log.d(TAG, msg);
    }

    public static void e(String TAG, String msg){
        android.util.Log.e(TAG, msg);
    }

    public static void v(String TAG, String msg){
        android.util.Log.v(TAG, msg);
    }

    public static void w(String TAG, String msg){
        android.util.Log.w(TAG, msg);
    }
}
