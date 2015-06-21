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
 * Captura de fotos
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ActivityFotoCapturada extends ActionBarActivity {
    private String nombreArchivo = null;
    private Usuario usuarioSeleccionado = null;
    private ImageView fotoCapturada = null;
    private File archivoFoto = null;
    private boolean estadoRed = false;
    private boolean fotoSubida = false;

    private double latitud = 0.0;
    private double longitud = 0.0;
    private Coordenadas coords = null;
    private String cadenaCoords = null;
    private File archivoCoords = null;
    private Location origen = null;

    private static final String FOTO_CAPTURADA = "FotoCapturada";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_capturada);
        usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
        fotoCapturada = (ImageView) this.findViewById(R.id.imageViewFoto);
    }

    public void sacarFoto(View view) {
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        File imageFolder = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camino/");
        PrintWriter out = null;

        nombreArchivo = usuarioSeleccionado.getNombre() + "_" + GestionConfigFicheros.getFechaHoraActual();
        archivoFoto = new File(imageFolder, nombreArchivo + ".png");
        archivoCoords = new File(imageFolder, nombreArchivo + ".dat");

        try {
            out = new PrintWriter(Environment.getExternalStorageDirectory() +
                    "/DCIM/Camino/" + nombreArchivo + ".dat");

            coords = new Coordenadas();
            origen = coords.getCoordenadas(this.getBaseContext());

            if (origen != null) {
                latitud = origen.getLatitude();
                longitud = origen.getLongitude();

                cadenaCoords = latitud + "," + longitud;
                out.print(cadenaCoords);

                fotoSubida = false;

                Uri uri = Uri.fromFile(archivoFoto);
                i.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(i, 1);
            } else {
                Toast.makeText(this, "No hay conexión GPS ni conexión a internet disponibles para " +
                        "obtener coords de la foto.", Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "No se puede crear el archivo con las coords de la foto.",
                    Toast.LENGTH_SHORT).show();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public void enviarFoto(View view) {
        if (!fotoSubida) {
            estadoRed = GestionConfigFicheros.comprobarConexion(this.getBaseContext());

            if (estadoRed) {
                AsyncTaskArchivosSubir task = new AsyncTaskArchivosSubir();

                try {
                    if (archivoFoto != null) {
                        Toast.makeText(this, "Subiendo foto al servidor.", Toast.LENGTH_SHORT).show();
                        fotoSubida = task.execute(archivoFoto, archivoCoords).get();

                        if (fotoSubida) {
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
                    "/DCIM/Camino/" + nombreArchivo + ".png");
            fotoCapturada.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_foto_capturada, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
