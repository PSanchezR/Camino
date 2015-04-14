package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Datos del usuario de la aplicación
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class DatosUsuario extends ActionBarActivity {

    protected String[] valoresComplexion = {"Nada deportista", "Poco deportista", "Deportista Amateur", "Deportista profesional"};
    private Usuario usuarioSeleccionado = null;
    private GestionFicheros archivador = new GestionFicheros();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);
        usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");

        ((TextView) findViewById(R.id.textViewNombre)).setText("Usuario: " + usuarioSeleccionado.getNombre());
        ((EditText) findViewById(R.id.editTextAltura)).setText("" + usuarioSeleccionado.getAltura());
        ((EditText) findViewById(R.id.editTextPeso)).setText("" + usuarioSeleccionado.getPeso());
        ((TextView) findViewById(R.id.textViewDistMax)).setText("   " + usuarioSeleccionado.getKmMaximos()+ " Km");


        ArrayAdapter adaptador = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, valoresComplexion);
        Spinner spinnerComplexion = (Spinner) findViewById(R.id.spinnerComplexion);
        spinnerComplexion.setAdapter(adaptador);
        spinnerComplexion.setSelection(usuarioSeleccionado.getComplexion());

    }

    //Método que edita los cambios del usuario seleccionado y guarda los mismos en el fichero de usuarios.
    public void aplicarCambios(View view)
    {
        usuarioSeleccionado.setAltura(Integer.parseInt(((EditText) findViewById(R.id.editTextAltura)).getText() + ""));
        usuarioSeleccionado.setPeso(Integer.parseInt(((EditText) findViewById(R.id.editTextPeso)).getText() + ""));
        usuarioSeleccionado.setComplexion(((Spinner) findViewById(R.id.spinnerComplexion)).getSelectedItemPosition());
        usuarioSeleccionado.calcularKmMaximos();
        archivador.escribirUsuarios(usuarioSeleccionado,getBaseContext());

        Intent i = new Intent(DatosUsuario.this, MenuPrincipal.class);
        i.putExtra("usuarioSeleccionado",usuarioSeleccionado);
        startActivity(i);

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
