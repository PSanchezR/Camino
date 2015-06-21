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
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Etapa seleccionada del camino actual
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityEtapaSeleccionada extends ActionBarActivity {
    private static final String ETAPA_SELECCIONADA = "EtapaSeleccionada";

    private Usuario usuarioSeleccionado = null;
    private Etapa etapaSeleccionada = null;

    private ArrayList<String> nombresParadas = new ArrayList<String>();

    private GoogleMap mapa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapa_seleccionada);

        this.usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("seleccionarUsuario");
        this.etapaSeleccionada = (Etapa) getIntent().getSerializableExtra("etapaSeleccionada");

        ListView lista = (ListView) findViewById(R.id.listViewListaParadas);
        ArrayAdapter<String> adaptador;

        ArrayList<Parada> listaParadas = this.etapaSeleccionada.getListaParadas();

        Iterator<Parada> itr = listaParadas.iterator();
        Parada parada = null;

        while (itr.hasNext()) {
            parada = itr.next();
            this.nombresParadas.add(parada.getNombre());
        }

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.nombresParadas);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> padre, View vista, int posicion, long id) {
                Parada parada = etapaSeleccionada.getListaParadas().get(posicion);
                Object elementos[] = {parada.getOrden(), parada.getNombre(), parada.getDistAnterior(),
                        parada.getDistSiguiente(), parada.getComida(), parada.getHotel(),
                        parada.getAlbergue(), parada.getFarmacia(), parada.getBanco(),
                        parada.getInternet()};

                Intent i = new Intent(ActivityEtapaSeleccionada.this, ActivityParadaSeleccionada.class);
                i.putExtra("seleccionarUsuario", usuarioSeleccionado);
                i.putExtra("paradaSeleccionada", elementos);
                startActivity(i);
            }
        });

        ((TextView) findViewById(R.id.textViewNombre)).setText("Etapa " + this.etapaSeleccionada.getOrden()
                + ": " + this.etapaSeleccionada.getNombre());
        String[] nombreParada = this.etapaSeleccionada.getNombre().split(" - ");

        ArrayList<LatLng> listaCoordsParadas = new ArrayList<LatLng>(this.etapaSeleccionada.getListaCoordsParadas());

        LatLng posInicial = listaCoordsParadas.get(0);
        LatLng posFinal = listaCoordsParadas.get(listaCoordsParadas.size() - 1);

        PolylineOptions puntos = new PolylineOptions();
        puntos.addAll(listaCoordsParadas);

        this.mapa = ((MapFragment) getFragmentManager().findFragmentById(R.id.fragmentMapa)).getMap();
        this.mapa.clear();

        this.mapa.addMarker(new MarkerOptions().position(posInicial).title(nombreParada[0]));
        this.mapa.addMarker(new MarkerOptions().position(posFinal).title(nombreParada[1]));

        this.mapa.addPolyline(puntos);

        this.mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(posInicial, 11.0f));
    }
}