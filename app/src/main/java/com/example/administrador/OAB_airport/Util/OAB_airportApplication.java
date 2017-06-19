package com.example.administrador.OAB_airport.Util;

import android.app.Application;

/**
 * Created by 71301836 on 14/05/2015.
 */
public class OAB_airportApplication  extends Application{

    public static OAB_airportApplication instance;

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }


    public static OAB_airportApplication getInstance() {
        return instance;
    }

    public static void setInstance(OAB_airportApplication instance) {
        OAB_airportApplication.instance = instance;
    }
}
