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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Ruta del Camino de Santiago que se está realizando
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class AccionCaminoActual extends ActionBarActivity {
    private GoogleMap map;
    private ArrayList<String> nombresEtapas = new ArrayList<String>();
    private GestionFicheros archivador = new GestionFicheros();
    private ArrayList<Parada> listaParadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camino_actual);

        this.listaParadas = archivador.parseadorXMLcaminos(getBaseContext());

        Usuario user = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        ListView lista = (ListView) findViewById(R.id.listViewEtapas);
        ArrayAdapter<String> adaptador;

        buscaNombresEtapas(user.getCaminoActual());
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresEtapas);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> padre, View vista, int posicion, long id) {
                String[] parts = nombresEtapas.get(posicion).split(" - ");
                Toast.makeText(getApplicationContext(), "Seleccionado: " + parts[0], Toast.LENGTH_SHORT).show();

                Iterator<Parada> itr = listaParadas.iterator();
                boolean encontrado = false;
                Parada parada = null;

                while (itr.hasNext() && !encontrado) {
                    parada = itr.next();

                    if (parada.getNombre().equals(parts[0])) {
                        encontrado = true;
                    }
                }

                //Toast.makeText(getApplicationContext(), "Encontrado: " + parada.getNombre(), Toast.LENGTH_SHORT).show();
                Parada paradaSiguiente = listaParadas.get(parada.getOrden()-1);
                LatLng posInicial = parada.getListaCoords().get(0);
                LatLng posFinal = paradaSiguiente.getListaCoords().get(0);

                PolylineOptions puntos = new PolylineOptions();
                puntos.addAll(parada.getListaCoords());

                map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                        .getMap();
                Marker marcaInicio = map.addMarker(new MarkerOptions().position(posInicial).title(parada.getNombre()));
                Marker marcaFin = map.addMarker(new MarkerOptions().position(posFinal).title(paradaSiguiente.getNombre()));

                Polyline linea = map.addPolyline(puntos);

                map.animateCamera(CameraUpdateFactory.newLatLngZoom(posInicial, 11.0f));
            }
        });

        /*
        lista.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                       // Intent i = new Intent(AccionCaminoActual.this, AccionMenuPrincipal.class);

                        //i.putExtra("usuarioSeleccionado", (Serializable) usuarioSeleccionado);
                        //startActivity(i);
                    }
                }
        );
        */
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
        // as you specify caminoFrances.xml parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
