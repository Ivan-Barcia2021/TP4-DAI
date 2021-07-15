package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;



public class MainActivity extends AppCompatActivity {
    //leer logcat y mirar tutorial de listviews custom
    EditText textoingresado= findViewById (R.id.textoingresado);
    JsonParser parseador= new JsonParser ();
    JsonObject objJsonCrudo;
    ArrayList<String> milistapeliculas= new ArrayList<> ();
    Pelicula mispeliculas= new Pelicula ();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);



    }
    private class tareaasincronica extends AsyncTask <Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids){
            try{
                //http://www.omdbapi.com/?apikey=[yourkey]&
                //6590bc69&s
                URL miurl= new URL ("http://www.omdbapi.com/?apikey=6590bc69&s=" + textoingresado);
                HttpURLConnection miconexion=(HttpURLConnection)miurl.openConnection ();
                Log.d("Acceso api", "me conecto");
                if(miconexion.getResponseCode ()==200){
                    Log.d("Acceso api", "conexion ok");
                    InputStream cuerporta= miconexion.getInputStream ();
                    InputStreamReader jsoncrudo= new InputStreamReader (cuerporta, "UTF-8");
                    procesar(jsoncrudo);

                }
                else {
                    Log.d("Acceso api", "error en la conexion");
                }
                miconexion.disconnect ();
            }

            catch (Exception error){
                Log.d("Acceso api", "Hubo un error"+ error.getMessage ());
            }
            return null;
        }
    }

    public void procesar(InputStreamReader jsoncrudo){

        String direccion;
        String año;
        String urlpelicula;
        objJsonCrudo=parseador.parse (jsoncrudo).getAsJsonObject ();

        try {
            direccion=objJsonCrudo.get("Director").getAsString ();
            año=objJsonCrudo.get("Year").getAsString ();
            urlpelicula=objJsonCrudo.get ("Poster").getAsString ();
            Log.d("LecturaJson", "Direccion"+""+ direccion);
            Log.d("LecturaJson", "Year"+""+ año);
            Log.d("LecturaJson", "Poster"+""+ urlpelicula);
            milistapeliculas= mispeliculas.obtenertodas ();
            ListView milist;
            milist=findViewById(R.id.lista1);
            adaptador miadaptador;
            miadaptador= new adaptador(milist, this);
            milist.setAdapter(miadaptador);
            milist.setOnItemClickListener(escuchador);

        }
        catch (Exception error){

        }

    }
    AdapterView.OnItemClikListener escuchador= new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l){

        }
    };
    public void buscar( View v){

        tareaasincronica mitarea= new tareaasincronica ();
        mitarea.execute ();



    }


//leer logcat y mirar tutorial de listviews custom


}