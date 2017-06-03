package com.example.administrador.OAB_airport;

import android.app.Application;

import com.example.administrador.OAB_airport.to.TOUsuario;

/**
 * Created by 71301836 on 14/05/2015.
 */
public class OAB_airportApplication  extends Application{

    public static OAB_airportApplication instance;
    public TOUsuario usuario;



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

    public TOUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(TOUsuario usuario) {
        this.usuario = usuario;
    }
}
