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

public class AccionMapaPrueba extends ActionBarActivity {
    static final LatLng SAINT_JEAN_PIED_DE_PORT = new LatLng(43.1569766, -1.2337874);
    static final LatLng SANTIAGO_DE_COMPOSTELA = new LatLng(42.8802049, -8.5447697);
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_prueba);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        Marker inicio = map.addMarker(new MarkerOptions().position(SAINT_JEAN_PIED_DE_PORT).title("Saint Jean Pied de Port"));
        Marker fin = map.addMarker(new MarkerOptions().position(SANTIAGO_DE_COMPOSTELA).title("Santiago de Compostela"));

        // Move the camera instantly to Saint Jean Pied de Port with caminoFrances.xml zoom of 2000.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(SAINT_JEAN_PIED_DE_PORT, 2000));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
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