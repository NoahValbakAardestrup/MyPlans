package com.example.noahvalbakaardestrup.myplans;

import android.app.Application;

public class MyPlanApplication extends Application {

    private static MyPlanApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static MyPlanApplication getInstance() {
        return instance;
    }
}
