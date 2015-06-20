package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/**
 * Listado de fotos en el servidor
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityFotoListado extends ActionBarActivity {
    private ArrayList<String> listaFotos = new ArrayList<String>();
    private static final String FOTO_LISTADO = "FotoListado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_listado);

        ListView lista = (ListView) findViewById(R.id.listViewListaFotos);
        ArrayAdapter<String> adaptador;

        Object dump = null;
        AsyncTaskArchivosListar task = new AsyncTaskArchivosListar();

        try {
            ArrayList<String> listado = task.execute(dump).get();

            Iterator<String> itr = listado.iterator();

            while (itr.hasNext()) {
                String nombre = itr.next();
                Log.d(ActivityFotoListado.FOTO_LISTADO, "Archivo recibido: " + nombre);
                listaFotos.add(nombre);
            }

            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaFotos);
            lista.setAdapter(adaptador);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> padre, View vista, int posicion, long id) {
                    String fotoSeleccionada = listaFotos.get(posicion);

                    Log.d(ActivityFotoListado.FOTO_LISTADO, "Foto seleccionada: " + fotoSeleccionada);

                    Intent i = new Intent(ActivityFotoListado.this, ActivityFotoSeleccionada.class);
                    i.putExtra("fotoSeleccionada", fotoSeleccionada);
                    startActivity(i);
                }
            });
        } catch (InterruptedException e) {
            Log.e(ActivityFotoListado.FOTO_LISTADO, "Error de interrupción: " + e.getMessage());
        } catch (ExecutionException e) {
            Log.e(ActivityFotoListado.FOTO_LISTADO, "Error de ejecución: " + e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_menu_principal, menu);
        return true;
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
