package com.dev.lin.camino;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;


public class Formulario_Nuevo_Usuario extends ActionBarActivity {
    Spinner lista;
    TextView texto;
    String[] complexiones = {"Nada deportista","Poco deportista","Deportista Amateur","Deportista profesional"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nuevo_usuario);
        ArrayAdapter adaptador = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,complexiones);
        lista = (Spinner) findViewById(R.id.spinnerComplexion);
        lista.setAdapter(adaptador);
    }

    public void escribirFicheroUsuarios(Usuario user)throws IOException
    {
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(openFileOutput("usuarios.obj", Context.MODE_PRIVATE));
            oos.writeObject(user);
            oos.close();
        }catch (Exception e){ Log.e("Tratamiento de ficheros","Error de escritura");}
    }

    public void crearUsuario()
    {
        Date fecha = new Date(Integer.parseInt(((EditText)findViewById(R.id.editTextFecha)).getText().toString()),1,1);
        int altura =Integer.parseInt(((EditText)findViewById(R.id.editTextAltura)).getText().toString());
        int peso = Integer.parseInt(((EditText) findViewById(R.id.editTextPeso)).getText().toString());
        String nombre = ((EditText)findViewById(R.id.editTextNombre)).getText().toString();
        Usuario user = new Usuario(peso,altura,fecha,nombre);

        try{
            escribirFicheroUsuarios(user);
        }catch(IOException e){Log.e("","Error de IO");}
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
