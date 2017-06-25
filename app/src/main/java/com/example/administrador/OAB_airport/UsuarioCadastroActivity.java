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
import android.text.TextUtils;

import com.example.administrador.OAB_airport.BD.DB_Controller;
import com.example.administrador.OAB_airport.Util.MaskEditTextChangedListener;

/**
 * Created by rafael on 04/06/17.
 */

public class UsuarioCadastroActivity extends ActionBarActivity implements View.OnClickListener {

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

        txtNome = (EditText) findViewById(R.id.txt_nome);
        txtCPF = (EditText) findViewById(R.id.txt_cpf);
        txtTelefone = (EditText) findViewById(R.id.txt_telefone);
        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtSenha = (EditText) findViewById(R.id.txt_senha);
        txtConfirmarSenha = (EditText) findViewById(R.id.txt_confirmar_senha);

        MaskEditTextChangedListener maskCPF = new MaskEditTextChangedListener("###.###.###-##", txtCPF);
        MaskEditTextChangedListener maskTEL = new MaskEditTextChangedListener("(##)#########", txtTelefone);
        txtCPF.addTextChangedListener(maskCPF);
        txtTelefone.addTextChangedListener(maskTEL);

        btnEntrar = (Button) findViewById(R.id.bt_Salvar);
        btnEntrar.setOnClickListener(this);

        controller = new DB_Controller(this, "", null, 5);
    }

    @Override
    public void onClick(View view) {
        if (txtNome.getText().toString().equals("")) {
            mensagem("Favor preencher o nome");
        } else if (txtCPF.getText().toString().equals("") ||
                !validateCPF(txtCPF.getText().toString())) {
            mensagem("CPF invalido");
        } else if (controller.validar_cpf(txtCPF.getText().toString())) {
            mensagem("CPF ja cadastrado");
        } else if (txtTelefone.getText().toString().equals("")) {
            mensagem("Favor preencher o telefone");
        } else if (txtEmail.getText().toString().equals("") || !validateEmail(txtEmail.getText().toString())) {
            mensagem("E-mail invalido");
        } else if (txtSenha.getText().toString().equals("") || txtConfirmarSenha.getText().toString().equals("")) {
            mensagem("Favor preencher a senha");
        } else if (!txtSenha.getText().toString().equals(txtConfirmarSenha.getText().toString())) {
            mensagem("Senhas não correspondem");
        } else {
            pd = new ProgressDialog(this);

            controller.insert_Usuario(txtNome.getText().toString(), txtCPF.getText().toString(), txtTelefone.getText().toString(), txtEmail.getText().toString(), txtSenha.getText().toString());

            pd.hide();
            pd.setMessage("Usuário criado com sucesso...");
            pd.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(UsuarioCadastroActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }
    }

    public static boolean validateCPF(String CPF) {
        CPF = CPF.replace(".", "").replace("-", "");
        if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")) {
            return false;
        }
        char dig10, dig11;
        int sm, i, r, num, peso;
        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48);
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return (true);
            else
                return (false);
        } catch (Exception erro) {
            return (false);
        }
    }

    public final static boolean validateEmail(String txtEmail) {
        if (txtEmail.matches("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+")) {
            return true;
        } else {
            return false;
        }
    }

    public void mensagem(String sms) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);

        dlgAlert.setMessage(sms);
        dlgAlert.setTitle("Atenção");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
}
