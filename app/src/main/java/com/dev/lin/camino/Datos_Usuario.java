package com.dev.lin.camino;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class Datos_Usuario extends ActionBarActivity {

    private Usuario  usuario_seleccionado = null;
    protected String[] valoresComplexion = {"Nada deportista","Poco deportista","Deportista Amateur","Deportista profesional"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);
        usuario_seleccionado = (Usuario)getIntent().getSerializableExtra("usuario_seleccionado");
        ((TextView)findViewById(R.id.textViewNombre)).setText("Usuario actual: "+usuario_seleccionado.getNombre());
        ((EditText)findViewById(R.id.editTextAltura)).setText("" + usuario_seleccionado.getAltura());
        ((EditText)findViewById(R.id.editTextPeso)).setText(""+usuario_seleccionado.getPeso());
        ArrayAdapter adaptador = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, valoresComplexion);
        Spinner spinnerComplexion = (Spinner) findViewById(R.id.spinnerComplexion);
        spinnerComplexion.setAdapter(adaptador);
        spinnerComplexion.setSelection(usuario_seleccionado.getComplexion());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_datos__usuario, menu);
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
