package com.dev.lin.camino;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

/**
 * Captura de fotos
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
        this.usuarioSeleccionado = (Usuario) getIntent().getSerializableExtra("usuarioSeleccionado");
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

                if (this.archivoFoto != null) {
                    task.execute(this.archivoFoto, this.archivoCoords);
                    this.fotoSubida = true;
                } else {
                    Toast.makeText(this, "No se ha capturado ninguna foto.", Toast.LENGTH_SHORT).show();
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

    public class AsyncTaskArchivosSubir extends AsyncTask<File, Void, Void> {
        private static final String ARCHIVOS_SUBIR = "ArchivosSubir";

        private ProgressDialog progressDialog;
        private FTPClient cliente = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ActivityFotoCapturada.this, "Subiendo foto",
                    "Por favor, espere...");
        }

        @Override
        public Void doInBackground(File... archivos) {
            int reply;
            boolean resultado = false;
            BufferedInputStream bis = null;

            this.cliente = new FTPClient();

            try {
                this.cliente.connect(ActivityMenuPrincipal.SERVIDOR, ActivityMenuPrincipal.PUERTO);
                this.cliente.login(ActivityMenuPrincipal.USUARIO, ActivityMenuPrincipal.PASS);
                this.cliente.changeWorkingDirectory(ActivityMenuPrincipal.DIRECTORIO);

                reply = this.cliente.getReplyCode();

                if (FTPReply.isPositiveCompletion(reply)) {
                    Log.d(AsyncTaskArchivosSubir.ARCHIVOS_SUBIR, "Conexión realizada con éxito.");

                    this.cliente.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
                    this.cliente.enterLocalPassiveMode();

                    for (int i = 0; i < archivos.length; i++) {
                        bis = new BufferedInputStream(new FileInputStream(archivos[i]));

                        Log.d(AsyncTaskArchivosSubir.ARCHIVOS_SUBIR, "Archivo a subir: " + archivos[i].getName());
                        resultado = this.cliente.storeFile(archivos[i].getName(), bis);

                        if (resultado) {
                            Log.d(AsyncTaskArchivosSubir.ARCHIVOS_SUBIR, archivos[i].getName() +
                                    " subido con éxito al servidor.");
                        } else {
                            Log.d(AsyncTaskArchivosSubir.ARCHIVOS_SUBIR, "No se ha podido subir + "
                                    + archivos[i].getName() + " al servidor.");
                        }

                        bis.close();
                        Log.e(AsyncTaskArchivosSubir.ARCHIVOS_SUBIR, "Cerrado flujo de entrada.");
                    }

                    this.cliente.logout();
                } else {
                    Log.d(AsyncTaskArchivosSubir.ARCHIVOS_SUBIR, "Conexión rechazada por el servidor.");
                    this.cliente.disconnect();
                }
            } catch (IOException e) {
                Log.e(AsyncTaskArchivosSubir.ARCHIVOS_SUBIR, "Error de entrada/salida: " + e.getMessage());
            } finally {
                try {
                    if (this.cliente.isConnected()) {
                        this.cliente.disconnect();
                        Log.e(AsyncTaskArchivosSubir.ARCHIVOS_SUBIR, "Desconectado del servidor.");
                    }
                } catch (IOException e) {
                    Log.e(AsyncTaskArchivosSubir.ARCHIVOS_SUBIR, "Error de entrada/salida: " + e.getMessage());
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void resultado) {
            super.onPostExecute(resultado);

            if (progressDialog.isShowing())
                progressDialog.dismiss();
        }
    }
}