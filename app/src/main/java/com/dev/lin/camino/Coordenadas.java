package com.dev.lin.camino;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Coordenadas de las fotos capturadas
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class Coordenadas implements LocationListener {
    private static final String COORDENADAS = "Coordenadas";

    private LocationManager locationManager;
    private Location coordenadas;

    public Coordenadas() {
        this.locationManager = null;
        this.coordenadas = null;
    }

    public Location getCoordenadas(Context ctx) {
        try {
            this.locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);

            boolean estadoGPS = this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean estadoRed = this.locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (estadoGPS) {
                if (this.coordenadas == null) {
                    this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                    if (this.locationManager != null) {
                        this.coordenadas = this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    }
                }
            } else if (estadoRed) {
                this.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

                if (this.locationManager != null) {
                    this.coordenadas = this.locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
            }
        } catch (Exception e) {
            Log.e(Coordenadas.COORDENADAS, "Error obteniendo coordenadas: " + e.getMessage());
        }

        return this.coordenadas;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
