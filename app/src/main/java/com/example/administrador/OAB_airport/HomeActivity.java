package com.example.administrador.OAB_airport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.administrador.OAB_airport.BD.DB_Controller;
import com.example.administrador.OAB_airport.BD.Session;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends ActionBarActivity implements View.OnClickListener{

    private ProgressDialog pd;
    ListView lista;
    List<String> reclamacao;
    List<String> statusIcon;
    List<String> idReclamacao;
    private DB_Controller controller;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        controller = new DB_Controller(this, "", null, 5);

        ImageButton btnreclamacao = (ImageButton)findViewById(R.id.bt_reclamacao);
        btnreclamacao.setOnClickListener(this);

        ImageButton btDocumento = (ImageButton)findViewById(R.id.bt_documento);
        btDocumento.setOnClickListener(this);

        session = new Session(this);
        session.getusename();

        lista = (ListView) findViewById(R.id.lista);
        reclamacao = new ArrayList<>();
        statusIcon = new ArrayList<>();
        idReclamacao = new ArrayList<>();

        Cursor cursor = controller.lista_Reclamacao(session.getusename());

        while (cursor.moveToNext()){

            reclamacao.add(cursor.getString(1)+" - "+cursor.getString(2));
            statusIcon.add(cursor.getString(2));
            idReclamacao.add(cursor.getString(0));
        }

        ListaAdapter adapter = new ListaAdapter(reclamacao, statusIcon, this);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this, ReclamacaoVisualizaActivity.class);
                intent.putExtra("IdReclamacao", idReclamacao.get(position));
                startActivity(intent);
            }
        });
    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bt_reclamacao:

                Cadastro();
                break;

            case R.id.bt_documento:

                Documento();
                break;
        }
    }

    public  void Cadastro(){
        pd = new ProgressDialog(this);
        pd.setMessage("Entrando...");
        pd.show();

        Intent intent = new Intent(HomeActivity.this, ReclamacaoCadastroActivity.class);
        startActivity(intent);
    }

    public  void Documento(){
        pd = new ProgressDialog(this);
        pd.setMessage("Entrando...");
        pd.show();

        Intent intent = new Intent(HomeActivity.this, DocumentoLeiActivity.class);
        startActivity(intent);
    }
}
