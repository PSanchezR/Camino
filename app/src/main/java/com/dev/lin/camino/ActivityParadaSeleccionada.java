package com.dev.lin.camino;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Parada seleccionada de una etapa del camino actual
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityParadaSeleccionada extends ActionBarActivity {
    private static final String PARADA_SELECCIONADA = "ParadaSeleccionada";

    private Object paradaSeleccionada[] = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parada_seleccionada);

        this.paradaSeleccionada = (Object[]) getIntent().getSerializableExtra("paradaSeleccionada");

        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.UP);

        ((TextView) findViewById(R.id.textViewNombre)).setText("Parada " + this.paradaSeleccionada[0] + ": "
                + this.paradaSeleccionada[1]);
        ((TextView) findViewById(R.id.textViewDistParadaAnt)).setText("Distancia parada anterior: " +
                df.format(this.paradaSeleccionada[2]) + " km");
        ((TextView) findViewById(R.id.textViewDistParadaSig)).setText("Distancia parada siguiente: " +
                df.format(this.paradaSeleccionada[3]) + " km");
        ((TextView) findViewById(R.id.textViewComida)).setText("¿Tiene bares/restaurantes?: " +
                GestionFicherosConfigs.boolString((Boolean) this.paradaSeleccionada[4]));
        ((TextView) findViewById(R.id.textViewHotel)).setText("¿Tiene hotel?: " +
                GestionFicherosConfigs.boolString((Boolean) this.paradaSeleccionada[5]));
        ((TextView) findViewById(R.id.textViewAlbergue)).setText("¿Tiene albergue?: " +
                GestionFicherosConfigs.boolString((Boolean) this.paradaSeleccionada[6]));
        ((TextView) findViewById(R.id.textViewFarmacia)).setText("¿Tiene farmacia?: " +
                GestionFicherosConfigs.boolString((Boolean) this.paradaSeleccionada[7]));
        ((TextView) findViewById(R.id.textViewBanco)).setText("¿Tiene banco?: " +
                GestionFicherosConfigs.boolString((Boolean) this.paradaSeleccionada[8]));
        ((TextView) findViewById(R.id.textViewInternet)).setText("¿Tiene establecimientos para conectarse " +
                "a internet?: " + GestionFicherosConfigs.boolString((Boolean) this.paradaSeleccionada[9]));
    }
}
