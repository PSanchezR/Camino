package com.dev.lin.camino;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

/**
 * Foto capturada
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityFotoCapturada extends ActionBarActivity {
    private static final String FOTO_CAPTURADA = "FotoCapturada";

    private Usuario usuarioSeleccionado = null;
    private File archivoFoto = null;

    private boolean estadoRed = false;
    private boolean fotoSubida = false;
    private double latitud = 0.0;
    private double longitud = 0.0;
    private String nombreArchivo = null;
    private String cadenaCoords = null;

    private Coordenadas coords = null;

    private ImageView fotoCapturada = null;
    private File archivoCoords = null;
    private Location origen = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_capturada);

        this.usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("seleccionarUsuario");

        this.fotoCapturada = (ImageView) this.findViewById(R.id.imageViewFoto);
    }

    public void sacarFoto(View view) {
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        File imageFolder = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camino/");
        PrintWriter out = null;

        this.nombreArchivo = this.usuarioSeleccionado.getNombre() + "_" + GestionFicherosConfigs.
                getFechaHoraActual();
        this.archivoFoto = new File(imageFolder, this.nombreArchivo + ".png");
        this.archivoCoords = new File(imageFolder, this.nombreArchivo + ".dat");

        try {
            out = new PrintWriter(Environment.getExternalStorageDirectory() +
                    "/DCIM/Camino/" + this.nombreArchivo + ".dat");

            this.coords = new Coordenadas();
            this.origen = this.coords.getCoordenadas(this.getBaseContext());

            if (this.origen != null) {
                this.latitud = this.origen.getLatitude();
                this.longitud = this.origen.getLongitude();

                this.cadenaCoords = this.latitud + "," + this.longitud;
                out.print(this.cadenaCoords);

                this.fotoSubida = false;

                Uri uri = Uri.fromFile(this.archivoFoto);

                i.putExtra("seleccionarUsuario", this.usuarioSeleccionado);
                i.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(i, 1);
            } else {
                Toast.makeText(this, "No hay conexión GPS ni conexión a internet disponibles para " +
                        "obtener coordenadas de la foto.", Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "No se puede crear el archivo con las coordenadas de la foto.",
                    Toast.LENGTH_SHORT).show();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public void enviarFoto(View view) {
        if (!this.fotoSubida) {
            this.estadoRed = GestionFicherosConfigs.comprobarConexion(this.getBaseContext());

            if (this.estadoRed) {
                AsyncTaskArchivosSubir task = new AsyncTaskArchivosSubir();

                try {
                    if (this.archivoFoto != null) {
                        Toast.makeText(this, "Subiendo foto al servidor.", Toast.LENGTH_SHORT).show();
                        this.fotoSubida = task.execute(this.archivoFoto, this.archivoCoords).get();

                        if (this.fotoSubida) {
                            Toast.makeText(this, "Foto subida al servidor.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "No se ha podido subir la foto al servidor.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "No se ha capturado ninguna foto.", Toast.LENGTH_SHORT).show();
                    }
                } catch (InterruptedException e) {
                    Log.e(ActivityFotoCapturada.FOTO_CAPTURADA, "Error de interrupción: " + e.getMessage());
                } catch (ExecutionException e) {
                    Log.e(ActivityFotoCapturada.FOTO_CAPTURADA, "Error de ejecución: " + e.getMessage());
                }
            } else {
                Toast.makeText(this, "No hay conexion a internet.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Esta foto ya ha sido subida al servidor.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int solicitud, int resultado, Intent datos) {
        if (solicitud == 1 && resultado == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() +
                    "/DCIM/Camino/" + this.nombreArchivo + ".png");
            this.fotoCapturada.setImageBitmap(bitmap);
        }
    }
}
