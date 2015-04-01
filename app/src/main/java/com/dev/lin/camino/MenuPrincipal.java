package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Menú principal de la aplicación
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class MenuPrincipal extends ActionBarActivity {

    private Usuario usuario_seleccionado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        usuario_seleccionado = (Usuario) getIntent().getSerializableExtra("usuario_seleccionado");
        ((TextView) findViewById(R.id.textViewNombre)).setText("Usuario actual: " + usuario_seleccionado.getNombre());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_principal, menu);
        return true;
    }

    public void datosUsuario(View view) {
        /*Insertar activity*/
        Intent i = new Intent(MenuPrincipal.this, DatosUsuario.class);
        i.putExtra("usuario_seleccionado", (Serializable) usuario_seleccionado);
        startActivity(i);
    }

    public void nuevoCamino(View view) {
        Intent i = new Intent(MenuPrincipal.this, NuevoCamino.class);
        i.putExtra("usuario_seleccionado", (Serializable) usuario_seleccionado);
        startActivity(i);
    }

    public void caminoActual(View view) {
        Intent i = new Intent(MenuPrincipal.this, CaminoActual.class);
        i.putExtra("usuario_seleccionado", (Serializable) usuario_seleccionado);
        startActivity(i);
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
