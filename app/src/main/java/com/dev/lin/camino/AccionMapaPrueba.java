package com.dev.lin.camino;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class AccionMapaPrueba extends ActionBarActivity {
    private GoogleMap map;
    private GestionFicheros archivador = new GestionFicheros();
    private ArrayList<Parada> listaParadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_prueba);

        this.listaParadas = archivador.parseadorXMLcaminos(getBaseContext());
        Parada inicio = this.listaParadas.get(0);
        LatLng posInicial = inicio.getListaCoords().get(0);

        PolylineOptions puntos = new PolylineOptions();
        puntos.addAll(this.listaParadas.get(0).getListaCoords());

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        Marker marca = map.addMarker(new MarkerOptions().position(posInicial).title(inicio.getNombre()));

        Polyline linea = map.addPolyline(puntos);

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(posInicial, 12.0f));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accion_mapa_prueba, menu);
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