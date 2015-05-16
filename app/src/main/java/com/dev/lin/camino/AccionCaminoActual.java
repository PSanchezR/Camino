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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Ruta del Camino de Santiago que se está realizando
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class AccionCaminoActual extends ActionBarActivity {
    private static final String DATOS_PARADA = "DatosParada";
    private GoogleMap map;
    private Marker inicio;
    private Marker fin;
    private ArrayList<String> nombresEtapas = new ArrayList<String>();
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camino_actual);

        user = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        ListView lista = (ListView) findViewById(R.id.listViewEtapas);
        ArrayAdapter<String> adaptador;

        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.UP);
        ArrayList<Etapa> listaEtapas = user.getCaminoActual().getListaEtapas();
        Iterator<Etapa> itr = listaEtapas.iterator();
        Etapa etapa = null;

        while (itr.hasNext()) {
            etapa = itr.next();
            nombresEtapas.add(etapa.getNombre() + ":\n" + df.format(etapa.getDistancia()) + " km");
        }

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresEtapas);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> padre, View vista, int posicion, long id) {
                String[] nombreEtapa = nombresEtapas.get(posicion).split(":\n");
                String[] nombreParada = nombreEtapa[0].split(" - ");
                Toast.makeText(getApplicationContext(), "Seleccionado: " + nombreEtapa[0], Toast.LENGTH_SHORT).show();

                ArrayList<Etapa> listaEtapas = user.getCaminoActual().getListaEtapas();
                Iterator<Etapa> itr2 = listaEtapas.iterator();
                Etapa etapa = null;
                boolean encontrado = false;

                while (itr2.hasNext() && !encontrado) {
                    etapa = itr2.next();

                    if (etapa.getNombre().equals(nombreEtapa[0])) {
                        encontrado = true;
                    }
                }

                Log.d(AccionCaminoActual.DATOS_PARADA, "Etapa: " + etapa.toString());

                ArrayList<LatLng> listaCoordsParadas = new ArrayList<LatLng>(etapa.getListaCoordsParadas());

                LatLng posInicial = listaCoordsParadas.get(0);
                LatLng posFinal = listaCoordsParadas.get(listaCoordsParadas.size() - 1);

                PolylineOptions puntos = new PolylineOptions();
                puntos.addAll(listaCoordsParadas);

                map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
                map.clear();

                map.addMarker(new MarkerOptions().position(posInicial).title(nombreParada[0]));
                map.addMarker(new MarkerOptions().position(posFinal).title(nombreParada[1]));

                map.addPolyline(puntos);

                map.animateCamera(CameraUpdateFactory.newLatLngZoom(posInicial, 11.0f));
            }
        });
    }

    public void menuPrincipal(View view) {
        Usuario usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        Intent i = new Intent(AccionCaminoActual.this, AccionMenuPrincipal.class);
        i.putExtra("usuarioSeleccionado", usuarioSeleccionado);
        startActivity(i);
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
