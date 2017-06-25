package com.example.administrador.OAB_airport;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.OAB_airport.BD.DB_Controller;

/**
 * Created by rafael on 03/06/17.
 */

public class ReclamacaoVisualizaActivity extends ActionBarActivity implements View.OnClickListener{

    private EditText txtStatus;
    private EditText txtNomeAeroporto;
    private EditText txtDescricao;
    private EditText txtDataHora;
    private String Reclamacao;

    private DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamacao_visualiza);

        Reclamacao = getIntent().getStringExtra("IdReclamacao");

        txtStatus = (EditText)findViewById(R.id.txt_Status);
        txtNomeAeroporto = (EditText)findViewById(R.id.txt_nome);
        txtDescricao = (EditText)findViewById(R.id.txt_descricao);
        txtDataHora = (EditText)findViewById(R.id.txt_dataHora);


        controller = new DB_Controller(this, "", null, 5);

        Cursor cursor = controller.Item_Reclamacao(Reclamacao);

        while (cursor.moveToNext()){
            txtNomeAeroporto.setText(cursor.getString(0));
            txtDescricao.setText(cursor.getString(1));
            txtStatus.setText(cursor.getString(2));
            txtDataHora.setText(cursor.getString(3));
        }

        Button btnRemover = (Button)findViewById(R.id.bt_remover);
        btnRemover.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tem certeza que deseja excluir este item?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Excluir();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void Excluir() {
        controller.delete_Reclamacao(Reclamacao);

        Intent intent = new Intent(ReclamacaoVisualizaActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}

