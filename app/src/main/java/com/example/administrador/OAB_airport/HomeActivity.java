package com.example.administrador.OAB_airport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends ActionBarActivity implements View.OnClickListener{

    private ProgressDialog pd;
    ListView lista;
    List<String> campeonatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton btnreclamacao = (ImageButton)findViewById(R.id.bt_reclamacao);
        btnreclamacao.setOnClickListener(this);

        lista = (ListView) findViewById(R.id.lista);

        campeonatos = new ArrayList<>();
        campeonatos.add("Brasileiro");
        campeonatos.add("Libertadores");
        campeonatos.add("Mineiro");
        campeonatos.add("Espanhol");
        campeonatos.add("Paulista");
        campeonatos.add("1");
        campeonatos.add("2");
        campeonatos.add("3");
        campeonatos.add("4");
        campeonatos.add("5");
        campeonatos.add("6");

        ListaAdapter adapter = new ListaAdapter(campeonatos, this);

        lista.setAdapter(adapter);
    }

    public void onClick(View v) {
        pd = new ProgressDialog(this);
        pd.setMessage("Entrando...");
        pd.show();

        Intent intent = new Intent(HomeActivity.this, ReclamacaoCadastroActivity.class);
        startActivity(intent);
        finish();
    }
}
