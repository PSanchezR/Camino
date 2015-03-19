package com.dev.lin.camino;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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

    public void crearUsuario()
    {
        Date fecha = new Date(R.id.editTextFecha);
        int altura = R.id.editTextAltura;
        int peso = R.id.editTextPeso;
        Usuario user = new Usuario(peso,altura,fecha);


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
