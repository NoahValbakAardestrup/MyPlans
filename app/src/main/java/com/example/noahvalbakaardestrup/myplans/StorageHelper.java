package com.trackunit.noahvalbakaardestrup.myplans;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.trackunit.noahvalbakaardestrup.myplans.model.PlanCollection;
import com.google.gson.Gson;

public class StorageHelper {


    public static boolean savePlanCollection(PlanCollection planCollection) {

        try {
            String dataToSave = new Gson().toJson(planCollection);

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyPlanApplication.getInstance());
            prefs.edit().putString("MYPLANS", dataToSave).apply();
            prefs.edit().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    public static PlanCollection readPlanCollection() {

        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyPlanApplication.getInstance());
            String dataLoaded = prefs.getString("MYPLANS", null);
            if (dataLoaded != null) {
                PlanCollection planCollection = new Gson().fromJson(dataLoaded, PlanCollection.class);
                return planCollection;
            }
        } catch (Exception ignored) {
        }
        return new PlanCollection();
    }
}
