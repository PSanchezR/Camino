package com.dev.lin.camino;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

/**
 * Datos de la etapa seleccionada del Camino de Santiago
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityEtapaSeleccionada extends ActionBarActivity {
    private Etapa etapaSeleccionada = null;
    private GoogleMap map;
    private Marker inicio;
    private Marker fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapa_seleccionada);
        etapaSeleccionada = (Etapa) getIntent().getSerializableExtra("etapaSeleccionada");
        ((TextView) findViewById(R.id.textViewNombre)).setText("Etapa " + etapaSeleccionada.getOrden() + ": " + etapaSeleccionada.getNombre());
        String[] nombreParada = etapaSeleccionada.getNombre().split(" - ");

        ArrayList<LatLng> listaCoordsParadas = new ArrayList<LatLng>(etapaSeleccionada.getListaCoordsParadas());

        LatLng posInicial = listaCoordsParadas.get(0);
        LatLng posFinal = listaCoordsParadas.get(listaCoordsParadas.size() - 1);

        PolylineOptions puntos = new PolylineOptions();
        puntos.addAll(listaCoordsParadas);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.fragmentMapa)).getMap();
        map.clear();

        map.addMarker(new MarkerOptions().position(posInicial).title(nombreParada[0]));
        map.addMarker(new MarkerOptions().position(posFinal).title(nombreParada[1]));

        map.addPolyline(puntos);

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(posInicial, 11.0f));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_etapa_seleccionada, menu);
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