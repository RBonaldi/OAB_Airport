package com.example.administrador.OAB_airport.BD;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by rafael on 18/06/17.
 */

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setusename(String usename) {
        prefs.edit().putString("idUser", usename).commit();
    }

    public String getusename() {
        String usename = prefs.getString("idUser","");
        return usename;
    }
}
