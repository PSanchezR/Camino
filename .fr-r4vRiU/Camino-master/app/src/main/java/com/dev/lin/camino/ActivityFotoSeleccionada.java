package com.dev.lin.camino;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Foto geoposicionada y mapa situado en esas coordenadas
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityFotoSeleccionada extends ActionBarActivity {
    private static final String FOTO_SELECCIONADA = "FotoSeleccionada";

    private String fotoSeleccionada = null;

    private boolean estadoRed = false;
    private boolean fotoDescargada = false;

    private File archivoCoords = null;
    private ImageView fotoCapturada = null;
    private Bitmap bitmap = null;
    private GoogleMap mapa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_seleccionada);

        this.fotoSeleccionada = (String) getIntent().getSerializableExtra("fotoSeleccionada");

        this.fotoCapturada = (ImageView) this.findViewById(R.id.imageViewFoto);
        this.estadoRed = GestionFicherosConfigs.comprobarConexion(this.getBaseContext());

        if (this.estadoRed) {
            AsyncTaskArchivosDescargar task = new AsyncTaskArchivosDescargar();

            try {
                this.fotoDescargada = task.execute(this.fotoSeleccionada).get();

                if (this.fotoDescargada) {
                    this.archivoCoords = new File(Environment.getExternalStorageDirectory() +
                            "/DCIM/Camino/" + this.fotoSeleccionada + ".dat");
                    this.bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() +
                            "/DCIM/Camino/" + this.fotoSeleccionada + ".png");
                    this.fotoCapturada.setImageBitmap(this.bitmap);

                    try {
                        FileReader lector = new FileReader(this.archivoCoords.getAbsolutePath());
                        BufferedReader br = new BufferedReader(lector);
                        String texto = br.readLine();
                        br.close();

                        String[] coordenadas = texto.split(",");
                        LatLng posInicial = new LatLng(Float.parseFloat(coordenadas[0]),
                                Float.parseFloat(coordenadas[1]));

                        this.mapa = ((MapFragment) getFragmentManager().findFragmentById(R.id.fragmentMapa)).
                                getMap();
                        this.mapa.clear();

                        this.mapa.addMarker(new MarkerOptions().position(posInicial).title(this.fotoSeleccionada));

                        this.mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(posInicial, 11.0f));
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
            Toast.makeText(this, "No hay conexión a internet.", Toast.LENGTH_SHORT).show();
        }
    }
}
