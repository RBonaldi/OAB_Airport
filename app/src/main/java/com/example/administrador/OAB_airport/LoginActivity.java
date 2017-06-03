package com.example.administrador.OAB_airport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.OAB_airport.fw.SharedPreferencesHelper;
import com.example.administrador.OAB_airport.task.LoginTask;
import com.example.administrador.OAB_airport.to.TOBase;
import com.example.administrador.OAB_airport.to.TOUsuario;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener{

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        Button btnEntrar = (Button)findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        pd = new ProgressDialog(this);
        pd.setMessage("Entrando...");
        pd.show();

        EditText edtUsuario = (EditText)findViewById(R.id.edtUsuario);
        String usuario = edtUsuario.getText().toString();
        EditText edtSenha = (EditText)findViewById(R.id.edtSenha);
        String senha = edtSenha.getText().toString();

        LoginTask login = new LoginTask(){
            @Override
            protected void onPostExecute(TOBase toBase) {

                pd.hide();

                if(toBase != null){
                    TOUsuario u = (TOUsuario)toBase;
                    if(u.isSucesso()){


                        SharedPreferencesHelper.write(LoginActivity.this, "user_preferences","user",u.toString());
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        OAB_airportApplication.getInstance().setUsuario(u);
                        startActivity(i);
                        finish();
                    }else{

                        Toast.makeText(LoginActivity.this, "Usuario e/ou senha inválidos", Toast.LENGTH_LONG).show();

                    }
                }

            }
        };

        login.execute(usuario, senha);

    }
}
