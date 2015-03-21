package com.dev.lin.camino;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;



public class Seleccion_Usuario extends ActionBarActivity {

    private
    ArrayList<Usuario> usuarios;
    ArrayList<String> users =new ArrayList<String>();
    ArrayAdapter<String> adapter;

    public void leerFicheroUsuarios() throws ClassNotFoundException, IOException
    {
        ObjectInputStream flujo = null;
        try
        {
            File f = new File("usuarios.obj");
            FileInputStream fis= new FileInputStream(f);
            flujo = new ObjectInputStream(fis);
            while(true)
            {
                Usuario us = (Usuario) flujo.readObject();
                usuarios.add(us);
            }
        }
        catch(Exception ex){Log.e("","Error al leer el fichero");}
        finally{flujo.close();}

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_usuario);
        leerFicheroUsuarios();

        if(usuarios.size()>0)
        {
            for(int i=0; i< usuarios.size();i++)
            {
                users.add(usuarios.get(i).getNombre());
            }
            adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,users);
            ListView lista = (ListView) findViewById(R.id.listUsuarios);
            lista.setAdapter(adapter);
        }

    }
    public void nuevoUsuario(View view)
    {
        Intent i = new Intent(Seleccion_Usuario.this,Formulario_Nuevo_Usuario.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seleccion_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
