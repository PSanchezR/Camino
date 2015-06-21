package com.dev.lin.camino;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.io.File;

/**
 * Portada de la aplicación
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityPortada extends ActionBarActivity {
    private static final String PORTADA = "Portada";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);

        GestionFicherosConfigs.generarParadasListadoCaminoFrances("caminoFrances.xml", getBaseContext());

        File imageFolder = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camino/");
        imageFolder.mkdirs();

        FrameLayout frame_fondo = (FrameLayout) findViewById(R.id.frameLayoutPortada);

        frame_fondo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(ActivityPortada.this, ActivityUsuarioSeleccionado.class);
                startActivity(intent);
                return false;
            }
        });
    }
}
