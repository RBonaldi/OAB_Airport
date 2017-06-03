package com.example.administrador.OAB_airport;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrador.OAB_airport.fw.SharedPreferencesHelper;
import com.example.administrador.OAB_airport.to.TOUsuario;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String user = SharedPreferencesHelper.read(MainActivity.this, "user_preferences", "user", null);

        if (user == null) {
            abreLogin();
        } else {
            TOUsuario u = TOUsuario.createByJson(user, TOUsuario.class);
            OAB_airportApplication.getInstance().setUsuario(u);

            abreHome();
        }


    }

    private void abreLogin() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }

    private void abreHome() {
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        }, 2000);


    }
}
