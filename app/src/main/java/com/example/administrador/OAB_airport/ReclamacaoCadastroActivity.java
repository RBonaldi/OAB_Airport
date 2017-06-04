package com.example.administrador.OAB_airport;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.EditText;

/**
 * Created by rafael on 03/06/17.
 */

public class ReclamacaoCadastroActivity extends ActionBarActivity implements View.OnClickListener{

    private ProgressDialog pd;
    private Switch swEstaNoAeroporto;
    private EditText txtNomeAeroporto;
    private TextView lbNomeAeroporto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamacao_cadastro);

        pd = new ProgressDialog(this);

        swEstaNoAeroporto = (Switch)findViewById(R.id.sw_EstaNoAeroporto);
        txtNomeAeroporto = (EditText)findViewById(R.id.txt_NomeAeroporto);
        lbNomeAeroporto = (TextView)findViewById(R.id.lb_NomeAeroporto);

        swEstaNoAeroporto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(swEstaNoAeroporto.isChecked())
                {

                    pd.setMessage("Abrindo mapa, por favor aguarde...");
                    pd.show();
                    txtNomeAeroporto.setVisibility(View.INVISIBLE);
                    lbNomeAeroporto.setVisibility(View.INVISIBLE);
                }
                else
                {
                    pd.setMessage("Fechando mapa, por favor aguarde...");
                    pd.show();
                    txtNomeAeroporto.setVisibility(View.VISIBLE);
                    lbNomeAeroporto.setVisibility(View.VISIBLE);
                }
                pd.hide();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

}

