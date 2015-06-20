package com.dev.lin.camino;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * Prueba de obtención de posición GPS
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityPruebaPosicionamiento extends ActionBarActivity {
    double latitud;
    double longitud;
    float distancia;
    Coordenadas coordenadas;
    Location origen;
    Location destino;
    LatLng coordsDestino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_posicionamiento);

        coordenadas = new Coordenadas();
        origen = coordenadas.getCoordenadas(this.getBaseContext());

        if (origen != null) {
            latitud = origen.getLatitude();
            longitud = origen.getLongitude();

            coordsDestino = GestionConfigFicheros.listaParadasCaminoFrances.get(0).getListaCoords().get(GestionConfigFicheros.listaParadasCaminoFrances.get(0).getListaCoords().size() - 1);
            destino = new Location("Destino");
            destino.setLatitude(coordsDestino.latitude);
            destino.setLongitude(coordsDestino.longitude);
            destino.setTime(new Date().getTime());

            distancia = origen.distanceTo(destino);

            ((TextView) findViewById(R.id.textViewOrigen)).setText("Coordenadas actuales: " + latitud + "," + longitud);
            ((TextView) findViewById(R.id.textViewDestino)).setText("Coordenadas coordsDestino: " + coordsDestino.latitude + "," + coordsDestino.longitude);
            ((TextView) findViewById(R.id.textViewDistancia)).setText("Distancia: " + distancia / 1000 + "km");
        } else {
            Toast.makeText(this, "No hay conexión GPS ni conexión a internet disponibles.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_prueba_posicionamiento, menu);
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
