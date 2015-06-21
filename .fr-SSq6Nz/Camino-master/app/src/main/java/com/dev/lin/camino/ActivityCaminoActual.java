package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
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
public class ActivityCaminoActual extends ActionBarActivity {
    private static final String CAMINO_ACTUAL = "CaminoActual";

    private Usuario usuarioSeleccionado = null;

    private ArrayList<String> nombresEtapas = new ArrayList<String>();

    private GoogleMap mapa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camino_actual);

        this.usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        ListView lista = (ListView) findViewById(R.id.listViewListaEtapas);
        ArrayAdapter<String> adaptador;

        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.UP);
        ArrayList<Etapa> listaEtapas = this.usuarioSeleccionado.getCaminoActual().getListaEtapas();

        this.dibujarMapaCamino(listaEtapas);

        Iterator<Etapa> itr = listaEtapas.iterator();
        Etapa etapa = null;

        while (itr.hasNext()) {
            etapa = itr.next();
            this.nombresEtapas.add(etapa.getNombre() + ":\n" + df.format(etapa.getDistancia()) + " km");
        }

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.nombresEtapas);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> padre, View vista, int posicion, long id) {
                String[] nombreEtapa = nombresEtapas.get(posicion).split(":\n");
                String[] nombreParada = nombreEtapa[0].split(" - ");
                Toast.makeText(getApplicationContext(), "Seleccionado: " + nombreEtapa[0], Toast.LENGTH_SHORT)
                        .show();

                ArrayList<Etapa> listaEtapas = usuarioSeleccionado.getCaminoActual().getListaEtapas();
                Iterator<Etapa> itr2 = listaEtapas.iterator();
                Etapa etapa = null;
                boolean encontrado = false;

                while (itr2.hasNext() && !encontrado) {
                    etapa = itr2.next();

                    if (etapa.getNombre().equals(nombreEtapa[0])) {
                        encontrado = true;
                    }
                }

                Intent i = new Intent(ActivityCaminoActual.this, ActivityEtapaSeleccionada.class);
                i.putExtra("usuarioSeleccionado", usuarioSeleccionado);
                i.putExtra("etapaSeleccionada", etapa);
                startActivity(i);
            }
        });
    }

    public void menuPrincipal(View view) {
        Intent i = new Intent(ActivityCaminoActual.this, ActivityMenuPrincipal.class);
        i.putExtra("usuarioSeleccionado", this.usuarioSeleccionado);
        startActivity(i);
    }

    public void dibujarMapaCamino(ArrayList<Etapa> listaEtapas) {
        ArrayList<LatLng> listaCoordsParadas = new ArrayList<LatLng>();

        Iterator<Etapa> itr = listaEtapas.iterator();
        Etapa etapa = null;
        LatLng coor = null;

        while (itr.hasNext()) {
            etapa = itr.next();

            Iterator<LatLng> itr2 = etapa.getListaCoordsParadas().iterator();

            while (itr2.hasNext()) {
                coor = itr2.next();
                listaCoordsParadas.add(coor);
            }

        }

        LatLng posInicial = listaCoordsParadas.get(0);
        LatLng posFinal = listaCoordsParadas.get(listaCoordsParadas.size() - 1);

        PolylineOptions puntos = new PolylineOptions();
        puntos.addAll(listaCoordsParadas);

        this.mapa = ((MapFragment) getFragmentManager().findFragmentById(R.id.fragmentMapa)).getMap();
        this.mapa.clear();

        this.mapa.addMarker(new MarkerOptions().position(posInicial).title("Inicio"));
        this.mapa.addMarker(new MarkerOptions().position(posFinal).title("Fin"));

        this.mapa.addPolyline(puntos);

        this.mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(posInicial, 7.0f));
    }
}
