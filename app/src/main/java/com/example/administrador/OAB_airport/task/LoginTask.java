package com.example.administrador.OAB_airport.task;

import android.os.AsyncTask;

import com.example.administrador.OAB_airport.fw.Util;
import com.example.administrador.OAB_airport.to.TOBase;
import com.example.administrador.OAB_airport.to.TOUsuario;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dirceu on 08/05/2015.
 */
public class LoginTask extends AsyncTask <String, Void, TOBase>{

    @Override
    protected TOBase doInBackground(String... params) {

        String usuario = params[0];
        String senha = params[1];

        TOUsuario u = null;

        try{

            // criando dados para postar na url
            String dados = "usuario=" + usuario + "&senha=" + senha;
            //String dados = "usuario=usuario@email.com&senha=senha123";

            URL url = new URL("http://apiexemplo.fourtime.com/services/usuario/autenticar");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            // setando método
            http.setRequestMethod("POST");
            // setando content type para requisição
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            http.setRequestProperty("Content-Length", Integer.toString(dados.length()));

            // definindo timeout de leitura
            http.setReadTimeout(15000);
            // definindo timeout da requisição
            http.setConnectTimeout(15000);
            // requisição tem entrada de dados
            http.setDoInput(true);
            // requisição tem retorno
            http.setDoOutput(true);

            // escrevendo dados em formato UTF-8
            OutputStream os = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(dados);
            writer.flush();
            writer.close();

            // executando post
            InputStream i = new BufferedInputStream(http.getInputStream());
            // obtendo resultado
            String result = Util.readString(i);
            u = TOUsuario.createByJson(result, TOUsuario.class);

        }catch(Exception e){
            u = null;
        }

        return u;
    }
}
