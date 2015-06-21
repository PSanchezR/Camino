package com.dev.lin.camino;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Datos de la parada seleccionada de una etapa
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityParadaSeleccionada extends ActionBarActivity {
    private Object paradaSeleccionada[] = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parada_seleccionada);
        paradaSeleccionada = (Object[]) getIntent().getSerializableExtra("paradaSeleccionada");

        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.UP);

        ((TextView) findViewById(R.id.textViewNombre)).setText("Parada " + paradaSeleccionada[0] + ": "
                + paradaSeleccionada[1]);
        ((TextView) findViewById(R.id.textViewDistParadaAnt)).setText("Distancia parada anterior: " +
                df.format(paradaSeleccionada[2]) + " km");
        ((TextView) findViewById(R.id.textViewDistParadaSig)).setText("Distancia parada siguiente: " +
                df.format(paradaSeleccionada[3]) + " km");
        ((TextView) findViewById(R.id.textViewComida)).setText("¿Tiene bares/restaurantes?: " +
                GestionConfigFicheros.boolString((Boolean) paradaSeleccionada[4]));
        ((TextView) findViewById(R.id.textViewHotel)).setText("¿Tiene hotel?: " +
                GestionConfigFicheros.boolString((Boolean) paradaSeleccionada[5]));
        ((TextView) findViewById(R.id.textViewAlbergue)).setText("¿Tiene albergue?: " +
                GestionConfigFicheros.boolString((Boolean) paradaSeleccionada[6]));
        ((TextView) findViewById(R.id.textViewFarmacia)).setText("¿Tiene farmacia?: " +
                GestionConfigFicheros.boolString((Boolean) paradaSeleccionada[7]));
        ((TextView) findViewById(R.id.textViewBanco)).setText("¿Tiene banco?: " +
                GestionConfigFicheros.boolString((Boolean) paradaSeleccionada[8]));
        ((TextView) findViewById(R.id.textViewInternet)).setText("¿Tiene establecimientos para conectarse " +
                "a internet?: " + GestionConfigFicheros.boolString((Boolean) paradaSeleccionada[9]));
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
