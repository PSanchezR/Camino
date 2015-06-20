package com.dev.lin.camino;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Coordenadas
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class Coordenadas implements LocationListener {
    private LocationManager locationManager;
    private Location coordenadas;

    private static final String COORDS_GPS = "Coordenadas";

    public Coordenadas() {
        this.locationManager = null;
        this.coordenadas = null;
    }

    public Location getCoordenadas(Context ctx) {
        try {
            locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);

            boolean estadoGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean estadoRed = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (estadoGPS) {
                if (coordenadas == null) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                    Toast.makeText(ctx, "Conexión GPS disponible.", Toast.LENGTH_SHORT).show();
                    if (locationManager != null) {
                        coordenadas = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    }
                }
            } else if (estadoRed) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
                Toast.makeText(ctx, "Conexión a internet disponible.", Toast.LENGTH_SHORT).show();
                if (locationManager != null) {
                    coordenadas = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
            }
        } catch (Exception e) {
            Log.e(Coordenadas.COORDS_GPS, "Error obteniendo coordenadas: " + e.getMessage());
        }

        return coordenadas;
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
