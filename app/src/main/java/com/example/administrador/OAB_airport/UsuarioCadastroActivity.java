package com.example.administrador.OAB_airport;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rafael on 04/06/17.
 */

public class UsuarioCadastroActivity extends ActionBarActivity implements View.OnClickListener{

    private ProgressDialog pd;
    private EditText txtNome;
    private EditText txtCPF;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtSenha;
    private EditText txtConfirmarSenha;
    private Button btnEntrar;

    private DB_Controller controller;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_cadastro);

        txtNome = (EditText)findViewById(R.id.txt_nome);
        txtCPF = (EditText)findViewById(R.id.txt_cpf);
        txtTelefone = (EditText)findViewById(R.id.txt_telefone);
        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtSenha = (EditText)findViewById(R.id.txt_senha);
        txtConfirmarSenha = (EditText)findViewById(R.id.txt_confirmar_senha);

        btnEntrar = (Button)findViewById(R.id.bt_Salvar);
        btnEntrar.setOnClickListener(this);

        controller = new DB_Controller(this, "", null, 1);
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

        if(txtNome.getText().toString().equals("")){
            dlgAlert.setMessage("Favor preencher o nome");
            dlgAlert.setTitle("Atenção");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
        else if(txtCPF.getText().toString().equals("")){
            dlgAlert.setMessage("Favor preencher o CPF");
            dlgAlert.setTitle("Atenção");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
        else if(txtTelefone.getText().toString().equals("")){
            dlgAlert.setMessage("Favor preencher o telefone");
            dlgAlert.setTitle("Atenção");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
        else if(txtEmail.getText().toString().equals("")){
            dlgAlert.setMessage("Favor preencher o email");
            dlgAlert.setTitle("Atenção");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

        }
        else if(txtSenha.getText().toString().equals("") || txtConfirmarSenha.getText().toString().equals("")){
            dlgAlert.setMessage("Favor preencher a senha");
            dlgAlert.setTitle("Atenção");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
        else if(!txtSenha.getText().toString().equals(txtConfirmarSenha.getText().toString())){
            dlgAlert.setMessage("Senhas não correspondem");
            dlgAlert.setTitle("Atenção");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
        else {
            pd = new ProgressDialog(this);

            controller.insert_Usuario(txtNome.getText().toString(), txtCPF.getText().toString(), txtTelefone.getText().toString(), txtEmail.getText().toString(), txtSenha.getText().toString());

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pd.hide();
                    pd.setMessage("Usuário criado com sucesso...");
                    pd.show();

                    Intent intent = new Intent(UsuarioCadastroActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                    pd.hide();
                }
            }, 2000);
        }
    }
}
