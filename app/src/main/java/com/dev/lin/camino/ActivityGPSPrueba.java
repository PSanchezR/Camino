package com.dev.lin.camino;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by germaaan on 16/06/15.
 */
public class ActivityGPSPrueba extends ActionBarActivity implements LocationListener{
    double latitud;
    double longitud;
    boolean gpsActivo;
    Location location;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_prueba);

        try {
            locationManager = (LocationManager)this.getBaseContext().getSystemService(LOCATION_SERVICE);
            gpsActivo = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }catch(Exception e){}

        if(gpsActivo){
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,60000,10,this);
            location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            latitud = location.getLatitude();
            longitud = location.getLongitude();
            ((TextView) findViewById(R.id.textViewCoordenadas)).setText(latitud+","+longitud);
        }
        //En caso contrario mostrar un toast de aviso
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_gps_prueba, menu);
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
