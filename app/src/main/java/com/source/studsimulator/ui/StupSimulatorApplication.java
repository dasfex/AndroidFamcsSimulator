package com.source.studsimulator.ui;

import android.app.Application;
import android.content.Context;

public class StupSimulatorApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getBaseContext();
    }

    public static Context getContext() {
        return context;
    }
}
