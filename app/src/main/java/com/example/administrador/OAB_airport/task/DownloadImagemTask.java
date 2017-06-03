package com.example.administrador.OAB_airport.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.example.administrador.OAB_airport.fw.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by dirceu on 17/04/2015.
 */
public class DownloadImagemTask extends AsyncTask<String, Void, Bitmap> {

    @Override
    protected Bitmap doInBackground(String... params) {

        String url = params[0];

        Bitmap b = obterImagem(url);

        if(b == null) {

            try {

                InputStream in = new java.net.URL(url).openStream();
                b = BitmapFactory.decodeStream(in);
                salvarBitmap(b, url);

            } catch (Exception e) {
                b = null;
            }

        }

        return b;
    }

    public void salvarBitmap(Bitmap b, String url) throws Exception{

        // criando id único para a imagem
        String id = Util.md5(url);

        // obtendo path das imagens
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/images/";

        // verificando se diretório existe
        File dir = new File(path);
        if(!dir.exists()){
            // se não existir cria
            dir.mkdir();
        }

        // criando filename
        String fileName = path + id + ".png";

        // log quando salvar imagem
        Log.i("Imagem", "Salvando imagem " + fileName);

        // criando output com o nome do arquivo
        FileOutputStream out = new FileOutputStream(fileName);
        // "comprimindo" imagem em um fileoutputstream
        b.compress(Bitmap.CompressFormat.JPEG, 100, out);
        // liberando memória
        out.flush();
        // fechando fileoutputstream
        out.close();

    }

    public Bitmap obterImagem(String url){

        // criando id único
        String id = Util.md5(url);

        // obtendo path da imagem
        String pathImagem = Environment.getExternalStorageDirectory().getAbsolutePath() + "/images/" + id + ".png";

        // verificando se a imagem existe
        File f = new File(pathImagem);
        if(f.exists()){
            Log.i("Imagem", "Obtendo imagem " + pathImagem);
            // se existe retorna bitmap da imagem
            return BitmapFactory.decodeFile(pathImagem);
        }else{
            // se não existe retorna null
            return null;
        }

    }





}
