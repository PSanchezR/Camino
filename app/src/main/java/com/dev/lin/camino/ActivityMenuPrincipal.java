package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Menú principal de la aplicación
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityMenuPrincipal extends ActionBarActivity {
    private Usuario usuarioSeleccionado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        ((TextView) findViewById(R.id.textViewNombre)).setText("Usuario: " + " " + usuarioSeleccionado.getNombre());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_menu_principal, menu);
        return true;
    }

    public void datosUsuario(View view) {
        Intent i = new Intent(ActivityMenuPrincipal.this, ActivityUsuarioDatos.class);
        i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
        startActivity(i);
    }

    public void caminoNuevo(View view) {
        Intent i = new Intent(ActivityMenuPrincipal.this, ActivityCaminoNuevo.class);
        i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
        startActivity(i);
    }

    public void caminoActual(View view) {
        if (usuarioSeleccionado.getCaminoActual() != null) {
            Intent i = new Intent(ActivityMenuPrincipal.this, ActivityCaminoActual.class);
            i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
            startActivity(i);
        } else {
            Toast.makeText(this, "No tiene asignado un camino actual", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ActivityMenuPrincipal.this, ActivityMenuPrincipal.class);
            i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
            startActivity(i);
        }
    }

    public void probarGPS(View view) {
        Intent i = new Intent(ActivityMenuPrincipal.this, ActivityGPSPrueba.class);
        i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
        startActivity(i);
    }

    public void fotoGeoposicionada(View view)
    {
        Intent i = new Intent(ActivityMenuPrincipal.this, ActivityfotoGeoposicionada.class);
        i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
        startActivity(i);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify caminoFrances.xml parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}