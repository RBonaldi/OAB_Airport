package com.example.administrador.OAB_airport;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.administrador.OAB_airport.fw.SharedPreferencesHelper;
import com.example.administrador.OAB_airport.task.LoginTask;
import com.example.administrador.OAB_airport.to.TOBase;
import com.example.administrador.OAB_airport.to.TOUsuario;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    private ProgressDialog pd;
    private DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        controller = new DB_Controller(this, "", null, 1);

        ImageButton btnEntrar = (ImageButton)findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(this);

        Button btnNovoUsuario = (Button)findViewById(R.id.btn_NovoUsuario);
        btnNovoUsuario.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnEntrar:

                logar();
                break;

            case R.id.btn_NovoUsuario:

                NovoUsuario();
                break;
        }
    }

    private void logar(){
        EditText edtUsuario = (EditText)findViewById(R.id.edtUsuario);
        String usuario = edtUsuario.getText().toString();
        EditText edtSenha = (EditText)findViewById(R.id.edtSenha);
        String senha = edtSenha.getText().toString();

        if(controller.validar_Usuario(usuario, senha)){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Email ou senha incorreta.");
            dlgAlert.setTitle("Atenção");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
    }

    private void NovoUsuario(){

        Intent intent = new Intent(LoginActivity.this, UsuarioCadastroActivity.class);
        startActivity(intent);
        finish();
    }
}
