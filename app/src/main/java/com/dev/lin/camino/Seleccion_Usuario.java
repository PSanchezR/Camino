package com.dev.lin.camino;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;



public class Seleccion_Usuario extends ActionBarActivity {

    private
    ArrayList<Usuario> usuarios;//Lista de objetos usuario que se leerán desde fichero.
    ArrayList<String> users =new ArrayList<String>();// Lista de nombres de usuario
    ArrayAdapter<String> adapter; //adaptador para pasar los nombres a un listview


    //Este método lee de un fichero los objetos usuario y los añade a una lista

    public void leerFicheroUsuarios()
    {
        ObjectInputStream flujo = null;
        try
        {
            usuarios = new ArrayList<Usuario>();
            flujo = new ObjectInputStream(openFileInput("usuarios.obj"));

            while(flujo!=null)
            {
                Usuario us = (Usuario)flujo.readObject();
                usuarios.add(us);
            }
            flujo.close();
        }
        catch(Exception ex){Log.e("Tratamiento de ficheros",ex.getMessage());}
    }

    //Este método carga los usuarios leidos del fichero en la ListView
    public void cargarUsuarios()
    {
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_usuario);

    }
    public void nuevoUsuario(View view)
    {
        Intent i = new Intent(Seleccion_Usuario.this,Formulario_Nuevo_Usuario.class);
        startActivity(i);
    }

    public void cargaUser(View view)
    {
        /*Insertar aqui carga del usuario de los posibles de la lista*/

        Intent i = new Intent(Seleccion_Usuario.this,MenuPrincipal.class);
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
