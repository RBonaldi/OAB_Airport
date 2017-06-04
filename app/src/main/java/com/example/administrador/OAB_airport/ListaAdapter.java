package com.example.administrador.OAB_airport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dirceu on 09/05/2017.
 */

public class ListaAdapter extends BaseAdapter {

    List<String> lista;
    Context context;

    public ListaAdapter(List<String> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lista.size();
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
        nome.setText(lista.get(position));

        // retornando objeto View v
        return v;
    }
}
