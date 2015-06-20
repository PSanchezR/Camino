package com.dev.lin.camino;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Listado de fotos en el servidor
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityFotoSeleccionada extends ActionBarActivity {
    private String fotoSeleccionada = null;
    private File archivo = null;
    private boolean fotoDescargada = false;
    private boolean estadoRed = false;
    private GoogleMap map = null;
    private Marker inicio = null;
    private static final String FOTO_SELECCIONADA = "FotoSeleccionada";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_seleccionada);
        fotoSeleccionada = (String) getIntent().getSerializableExtra("fotoSeleccionada");

        estadoRed = GestionConfigFicheros.comprobarConexion(this.getBaseContext());

        if (estadoRed) {
            AsyncTaskArchivosDescargar task = new AsyncTaskArchivosDescargar();

            try {
                fotoDescargada = task.execute(fotoSeleccionada).get();

                if (fotoDescargada) {
                    archivo = new File(Environment.getExternalStorageDirectory() +
                            "/DCIM/Camino/" + fotoSeleccionada + ".dat");

                    try {
                        FileReader lector = new FileReader(archivo.getAbsolutePath());
                        BufferedReader br = new BufferedReader(lector);
                        String texto = br.readLine();
                        br.close();

                        String[] coordenadas = texto.split(",");
                        LatLng posInicial = new LatLng(Float.parseFloat(coordenadas[0]),
                                Float.parseFloat(coordenadas[1]));

                        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.fragmentMapa)).getMap();
                        map.clear();

                        map.addMarker(new MarkerOptions().position(posInicial).title(fotoSeleccionada));

                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(posInicial, 11.0f));
                    } catch (FileNotFoundException e) {
                        Log.e(ActivityFotoSeleccionada.FOTO_SELECCIONADA, "Archivo no encontrado: "
                                + e.getMessage());
                    } catch (IOException e) {
                        Log.e(ActivityFotoSeleccionada.FOTO_SELECCIONADA, "Error de entrada/salida: "
                                + e.getMessage());
                    }
                } else {
                    Toast.makeText(this, "No se ha podido descargar la foto al servidor.",
                            Toast.LENGTH_SHORT).show();
                }
            } catch (InterruptedException e) {
                Log.e(ActivityFotoSeleccionada.FOTO_SELECCIONADA, "Error de interrupción: " + e.getMessage());
            } catch (ExecutionException e) {
                Log.e(ActivityFotoSeleccionada.FOTO_SELECCIONADA, "Error de ejecución: " + e.getMessage());
            }
        } else {
            Toast.makeText(this, "Esta foto ya ha sido subida al servidor.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_foto_seleccionada, menu);
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
