package com.dev.lin.camino;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/**
 * Galería con imágenes geoposicionadas y mapas contenidos en el servidor
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityGaleria extends ActionBarActivity {
    private ArrayList<String> listaFotos = new ArrayList<String>();
    private static final String GALERIA = "Galeria";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        ListView lista = (ListView) findViewById(R.id.listViewListaFotos);
        ArrayAdapter<String> adaptador;

        Object dump = null;
        AsyncTaskListarArchivos task = new AsyncTaskListarArchivos();

        try {
            ArrayList<String> listado = task.execute(dump).get();

            Iterator<String> itr = listado.iterator();

            while (itr.hasNext()) {
                String nombre = itr.next();
                Log.d(ActivityGaleria.GALERIA, "Archivo recibido: " + nombre);
                listaFotos.add(nombre);
            }

            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaFotos);
            lista.setAdapter(adaptador);
        } catch (InterruptedException e) {
            Log.e(ActivityGaleria.GALERIA, "Error de interrupción: " + e.getMessage());
        } catch (ExecutionException e) {
            Log.e(ActivityGaleria.GALERIA, "Error de ejecución: " + e.getMessage());
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
