package com.example.administrador.OAB_airport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by rafael on 25/06/17.
 */


public class DocumentoLeiActivity extends ActionBarActivity implements View.OnClickListener {

    private Button btnEntrar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documento_lei);

        btnEntrar = (Button) findViewById(R.id.bt_entrar);
        btnEntrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(DocumentoLeiActivity.this, ReclamacaoCadastroActivity.class);
        startActivity(intent);
        finish();
    }
}
