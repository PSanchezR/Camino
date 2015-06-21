package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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
    private static final String FOTO_LISTADO = "FotoListado";

    private Usuario usuarioSeleccionado = null;

    private ArrayList<String> listaFotos = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_listado);

        this.usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");

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
                this.listaFotos.add(nombre);
            }

            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.listaFotos);
            lista.setAdapter(adaptador);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> padre, View vista, int posicion, long id) {
                    String fotoSeleccionada = listaFotos.get(posicion);

                    Log.d(ActivityFotoListado.FOTO_LISTADO, "Foto seleccionada: " + fotoSeleccionada);

                    Intent i = new Intent(ActivityFotoListado.this, ActivityFotoSeleccionada.class);
                    i.putExtra("usuarioSeleccionado", usuarioSeleccionado);
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
}
