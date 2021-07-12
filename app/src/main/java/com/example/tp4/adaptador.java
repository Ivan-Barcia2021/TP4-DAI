package com.example.tp4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adaptador extends BaseAdapter {
    private ArrayList<String> _listapeliculas;
    private Context _micontexto;
    public adaptador(ArrayList<String> milistapeliculas, Context contextoausar){
        _listapeliculas= milistapeliculas;
        _micontexto=contextoausar;
    }
    public int getCount(){
        return _listapeliculas.size();

    }
    public String getItem(int pos){
        String nombre;
        nombre=_listapeliculas.get(pos);
        return  nombre;
    }
    public long getItemId(int pos){
        return pos;
    }
    public View getView(int pos, View vista, ViewGroup grupo){
        View vistadevolver;
        vistadevolver=null;
        LayoutInflater inflador;
        inflador=(LayoutInflater)_micontexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vistadevolver=inflador.inflate(R.layout.listviewpeliculas, grupo,false);
        return vistadevolver;
        TextView txttitulo;
        txttitulo=(TextView)vistadevolver.findViewById(R.id.mipelicula);
    }

}
