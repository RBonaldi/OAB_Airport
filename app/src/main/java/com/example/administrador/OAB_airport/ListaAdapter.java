package com.example.administrador.OAB_airport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dirceu on 09/05/2017.
 */

public class ListaAdapter extends BaseAdapter {

    List<String> listaAeroporto;
    List<String> listaStatus;
    Context context;

    public ListaAdapter(List<String> listaAeroporto, List<String> listaStatus, Context context) {
        this.listaAeroporto = listaAeroporto;
        this.listaStatus = listaStatus;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaAeroporto.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // obtendo objeto inflater para inflar o xml de layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // inflando xml de layout item_lista
        View v = inflater.inflate(R.layout.item_lista, null);

        // buscando objeto nome no xml item_lista
        TextView nome = (TextView) v.findViewById(R.id.nome);

        // setando texto na posição "position"
        nome.setText(listaAeroporto.get(position));

        ImageView img = (ImageView) v.findViewById(R.id.img_Status);

        if(listaStatus.get(position).toString().equals("Enviado")) {
            img.setImageResource(R.drawable.enviado);
        }
        else if(listaStatus.get(position).toString().equals("Em Avaliação")) {
            img.setImageResource(R.drawable.avaliacao);
        }
        else if(listaStatus.get(position).toString().equals("Aprovado")) {
            img.setImageResource(R.drawable.aprovado);
        }
        else {
            img.setImageResource(R.drawable.reprovado);
        }

        // setando texto na posição "position"


        // retornando objeto View v
        return v;
    }
}
