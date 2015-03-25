package com.dev.lin.camino;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Formulario_Nuevo_Usuario extends ActionBarActivity{
    protected String archivo = "usuarios.dat";
    protected Usuario usuario;
    protected Spinner listaComplexion;
    protected String[] valoresComplexion = {"Nada deportista","Poco deportista","Deportista Amateur","Deportista profesional"};

    //TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nuevo_usuario);
        ArrayAdapter adaptador = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, valoresComplexion);
        listaComplexion = (Spinner) findViewById(R.id.spinnerComplexion);
        listaComplexion.setAdapter(adaptador);
    }

    public void guardarUsuarios(Usuario usuario) {
        FileOutputStream fos;
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>)getIntent().getSerializableExtra("usuarios");
        usuarios.add(usuario);
        try {
            fos = openFileOutput(archivo, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for(int i = 0; i < usuarios.size();i++)
            {
                oos.writeObject(usuarios.get(i));
            }
            oos.close();


            Toast.makeText(this, "Usuario guardado correctamente.", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            System.err.println("Error: archivo no encontrado.");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.err.println("Error: problema de entrada/salida.");
            e.printStackTrace();
        }
    }

    public void crearUsuario(View view) {
        String nombre = ((EditText)findViewById(R.id.editTextNombre)).getText().toString();
        int altura =Integer.parseInt(((EditText)findViewById(R.id.editTextAltura)).getText().toString());
        int peso = Integer.parseInt(((EditText) findViewById(R.id.editTextPeso)).getText().toString());
        int complexion = ((Spinner)findViewById(R.id.spinnerComplexion)).getSelectedItemPosition();
        int anioDeNacimiento = Integer.parseInt(((EditText)findViewById(R.id.editTextFecha)).getText().toString());
        Usuario usuario = new Usuario(nombre, altura, peso, complexion, anioDeNacimiento);

        if(existeUsuario(nombre))
        {
            Toast.makeText(this, "Ya existe un usuario con ese nombre. Pruebe con otro", Toast.LENGTH_SHORT).show();

        }else
        {
            guardarUsuarios(usuario);
            menuUsuarios();
        }
    }

    public boolean existeUsuario(String nombre)
    {
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>)getIntent().getSerializableExtra("usuarios");
        for (int i = 0; i < usuarios.size();i++)
        {
            if(usuarios.get(i).getNombre().compareTo(nombre)==0)
            {
                return true;
            }
        }
        return false;
    }
    public void menuUsuarios()
    {
        Intent intent = new Intent(Formulario_Nuevo_Usuario.this, Seleccion_Usuario.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario_nuevo_usuario, menu);
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
