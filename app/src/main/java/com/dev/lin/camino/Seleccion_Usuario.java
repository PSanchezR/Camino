package com.dev.lin.camino;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;



public class Seleccion_Usuario extends ActionBarActivity {


    static ArrayList<Usuario> usuarios;//Lista de objetos usuario que se leerán desde fichero.
    private
    ArrayList<String> users =new ArrayList<String>();// Lista de nombres de usuario
    ArrayAdapter<String> adapter; //adaptador para pasar los nombres a un listview
    String seleccionado = null;
    Usuario usuario_seleccionado = null;
    //Este método lee de un fichero los objetos usuario y los agrupa

    public void leerFicheroUsuarios()
    {
        FileInputStream fis;
        ObjectInputStream ois = null;
        Object aux;
        try
        {
            fis = openFileInput("usuarios.dat");
            usuarios = new ArrayList<Usuario>();
            ois = new ObjectInputStream(fis);
            aux = ois.readObject();
            while(aux!=null)
            {
                Usuario us = (Usuario)aux;
                usuarios.add(us);
                aux = ois.readObject();

            }
            ois.close();
        }
        catch(FileNotFoundException ex){
            System.err.println("Error: archivo no encontrado.");
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex)
        {
            System.err.println("Error: Clase no encontrada.");
            ex.printStackTrace();
        }catch(IOException ex)
        {
            System.err.println("Error: Problema de entrada-salida");
            ex.printStackTrace();
        }
    }



    //Este método carga los usuarios leidos del fichero en la ListView
    public void cargarUsuarios()
    {
        /*Gestion_Ficheros gf = new Gestion_Ficheros();
        usuarios =  gf.*/
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
        cargarUsuarios();
        usuarioSeleccionado();
    }
    public void nuevoUsuario(View view)
    {
        Intent i = new Intent(Seleccion_Usuario.this,Formulario_Nuevo_Usuario.class);
        i.putExtra("usuarios",(Serializable)usuarios);
        startActivity(i);
    }
    public void buscarUsuario()
    {
        for(int i = 0; i < usuarios.size();i++)
        {
            if(usuarios.get(i).getNombre().compareTo(seleccionado)==0)
            {
                usuario_seleccionado=usuarios.get(i);
            }
        }
    }


    public void usuarioSeleccionado()
    {
        ListView lista = (ListView)findViewById(R.id.listUsuarios);
        ArrayAdapter<String> adaptador;
        adaptador= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,users);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> a, View v,int position, long id)
                    {
                       seleccionado = (String)a.getItemAtPosition(position);
                        buscarUsuario();
                        Intent i = new Intent(Seleccion_Usuario.this,MenuPrincipal.class);
                        i.putExtra("usuario_seleccionado",(Serializable)usuario_seleccionado);
                        startActivity(i);
                    }
                }
        );
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
