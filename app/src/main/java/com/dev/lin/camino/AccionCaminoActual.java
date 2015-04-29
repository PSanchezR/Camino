package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Ruta del Camino de Santiago que se está realizando
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class AccionCaminoActual extends ActionBarActivity {
    private ArrayList<String> nombresEtapas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camino_actual);
        Usuario user = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        ListView lista = (ListView) findViewById(R.id.listViewEtapas);
        ArrayAdapter<String> adaptador;

        buscaNombresEtapas(user.getCaminoActual());
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresEtapas);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                       /* Intent i = new Intent(AccionCaminoActual.this, AccionMenuPrincipal.class);

                        i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
                        startActivity(i);*/
                    }
                }
        );

    }

    public void menuPrincipal(View view) {
        Usuario usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        Intent i = new Intent(AccionCaminoActual.this, AccionMenuPrincipal.class);
        i.putExtra("usuarioSeleccionado", usuarioSeleccionado);
        startActivity(i);
    }

    private void buscaNombresEtapas(Camino c) {
        ArrayList<Etapa> etapas = c.getListaEtapas();
        Etapa et;
        for (int i = 0; i < etapas.size(); i++) {
            et = etapas.get(i);
            nombresEtapas.add(et.getNombre() + ": " + et.getKMs() + " kms.");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accion_camino_actual, menu);
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
