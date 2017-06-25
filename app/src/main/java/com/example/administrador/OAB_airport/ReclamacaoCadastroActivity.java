package com.example.administrador.OAB_airport;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.EditText;

import com.example.administrador.OAB_airport.BD.DB_Controller;
import com.example.administrador.OAB_airport.BD.Session;
import com.example.administrador.OAB_airport.Util.MaskEditTextChangedListener;

import java.text.SimpleDateFormat;

/**
 * Created by rafael on 03/06/17.
 */

public class ReclamacaoCadastroActivity extends ActionBarActivity implements View.OnClickListener{

    private ProgressDialog pd;
    private Switch swEstaNoAeroporto;
    private EditText txtNomeAeroporto;
    private EditText txtDescricao;
    private EditText txtDataHora;

    private DB_Controller controller;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamacao_cadastro);

        pd = new ProgressDialog(this);

        swEstaNoAeroporto = (Switch)findViewById(R.id.sw_estaNoAeroporto);
        txtNomeAeroporto = (EditText)findViewById(R.id.txt_nome);
        txtDescricao = (EditText)findViewById(R.id.txt_descricao);
        txtDataHora = (EditText)findViewById(R.id.txt_dataHora);
        MaskEditTextChangedListener maskDataHora = new MaskEditTextChangedListener("##/##/#### - ##:##", txtDataHora);
        txtDataHora.addTextChangedListener(maskDataHora);

        controller = new DB_Controller(this, "", null, 5);

        swEstaNoAeroporto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(swEstaNoAeroporto.isChecked())
                {
                    long date = System.currentTimeMillis();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - h:mm");
                    String dateString = sdf.format(date);
                    txtDataHora.setText(dateString);
                }
                else
                {
                    txtDataHora.setText("");
                }
                pd.hide();
            }
        });

        Button btnSalvar = (Button)findViewById(R.id.bt_salvar);
        btnSalvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (txtDataHora.getText().toString().equals("")) {
            mensagem("Favor preencher a data e hora");
        } else if (txtNomeAeroporto.getText().toString().equals("")) {
            mensagem("Favor preencher o aeroporto/Estado");
        } else if (txtDescricao.getText().toString().equals("")) {
            mensagem("Favor descrever o problema");
        } else {
            pd = new ProgressDialog(this);

            session = new Session(this);
            controller.insert_Reclamacao(session.getusename(), txtNomeAeroporto.getText().toString(), txtDescricao.getText().toString(), txtDataHora.getText().toString());

            pd.hide();
            pd.setMessage("Reclamação criado com sucesso...");
            pd.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {


                    Intent intent = new Intent(ReclamacaoCadastroActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
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

