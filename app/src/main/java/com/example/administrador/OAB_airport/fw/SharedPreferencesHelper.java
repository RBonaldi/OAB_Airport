package com.example.administrador.OAB_airport.fw;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 71301836 on 14/05/2015.
 */
public class SharedPreferencesHelper {

    public static boolean write(Context context, String file, String key, String value) {
        boolean success = false;
        try {
            SharedPreferences sp = context.getApplicationContext().getSharedPreferences(file, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(key, value);
            editor.commit();
            success = true;

        } catch (Exception e) {
            return false;
        }
        return success;
    }

    public static String read(Context context, String file, String key, String defaultValue) {
        String value = null;
        try {
            SharedPreferences sp = context.getApplicationContext().getSharedPreferences(file, Context.MODE_PRIVATE);
            value = sp.getString(key, defaultValue);
        } catch (Exception e) {
            value = defaultValue;
        }
        return value;
    }
}