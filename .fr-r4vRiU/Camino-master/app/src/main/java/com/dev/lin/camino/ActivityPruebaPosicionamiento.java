package com.dev.lin.camino;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
    private static final String PRUEBA_POSICIONAMIENTO = "PruebaPosicionamiento";

    double latitud = 0.0;
    double longitud = 0.0;
    float distancia = 0.0f;

    Coordenadas coordenadas = null;
    LatLng coordsDestino = null;
    Location origen = null;
    Location destino = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_posicionamiento);

        this.coordenadas = new Coordenadas();
        this.origen = this.coordenadas.getCoordenadas(this.getBaseContext());

        if (this.origen != null) {
            this.latitud = this.origen.getLatitude();
            this.longitud = this.origen.getLongitude();

            this.coordsDestino = GestionFicherosConfigs.listaParadasCaminoFrances.get(0).getListaCoords().
                    get(GestionFicherosConfigs.listaParadasCaminoFrances.get(0).getListaCoords().size() - 1);
            this.destino = new Location("Destino");
            this.destino.setLatitude(this.coordsDestino.latitude);
            this.destino.setLongitude(this.coordsDestino.longitude);
            this.destino.setTime(new Date().getTime());

            this.distancia = this.origen.distanceTo(this.destino);

            ((TextView) findViewById(R.id.textViewOrigen)).setText("Coordenadas actuales: " + this.latitud +
                    "," + this.longitud);
            ((TextView) findViewById(R.id.textViewDestino)).setText("Coordenadas coordsDestino: " +
                    this.coordsDestino.latitude + "," + this.coordsDestino.longitude);
            ((TextView) findViewById(R.id.textViewDistancia)).setText("Distancia: " + this.distancia / 1000
                    + "km");
        } else {
            Toast.makeText(this, "No hay conexión GPS ni conexión a internet disponibles.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
