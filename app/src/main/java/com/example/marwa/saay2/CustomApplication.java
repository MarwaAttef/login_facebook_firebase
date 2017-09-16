package com.example.marwa.saay2;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by Marwa on 9/16/2017.
 */
public class CustomApplication extends Application {

    private static Context context;
    public void onCreate() {
        super.onCreate();
        CustomApplication.context = getApplicationContext();
    }
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    public static Context getAppContext() {
        return CustomApplication.context;
    }

}
