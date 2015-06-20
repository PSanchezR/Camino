package com.dev.lin.camino;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Tarea asíncrona para descargar las fotos que se visualizarán en la galeria
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class AsyncTaskArchivosDescargar extends AsyncTask<String, Void, Boolean> {
    private FTPClient cliente = null;
    private static final String ARCHIVOS_DESCARGAR = "ArchivosDescagar";

    public Boolean doInBackground(String... archivos) {
        int reply;
        boolean resultado = false;

        cliente = new FTPClient();
        try {
            cliente.connect(ActivityMenuPrincipal.SERVIDOR, ActivityMenuPrincipal.PUERTO);
            cliente.login(ActivityMenuPrincipal.USUARIO, ActivityMenuPrincipal.PASS);
            cliente.changeWorkingDirectory(ActivityMenuPrincipal.DIRECTORIO);

            reply = cliente.getReplyCode();

            if (FTPReply.isPositiveCompletion(reply)) {
                Log.d(AsyncTaskArchivosDescargar.ARCHIVOS_DESCARGAR, "Conexión realizada con éxito.");

                cliente.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
                cliente.enterLocalPassiveMode();
                String extensiones[] = {".png", ".dat"};

                for (int i = 0; i < extensiones.length; i++) {
                    String archivoRemoto = archivos[0] + extensiones[i];
                    File archivoDescargado = new File(Environment.getExternalStorageDirectory() +
                            "/DCIM/Camino/" + archivoRemoto);
                    OutputStream out = new BufferedOutputStream(new FileOutputStream(archivoDescargado));
                    resultado = cliente.retrieveFile(archivoRemoto, out);
                    out.close();

                    if (resultado) {
                        Log.d(AsyncTaskArchivosDescargar.ARCHIVOS_DESCARGAR, "Archivo " + archivoRemoto +
                                " descargado correctamente en " + archivoDescargado.getAbsolutePath());
                    }
                }

                cliente.logout();
            } else {
                Log.d(AsyncTaskArchivosDescargar.ARCHIVOS_DESCARGAR, "Conexión rechazada por el servidor.");
                cliente.disconnect();
            }
        } catch (IOException e) {
            Log.e(AsyncTaskArchivosDescargar.ARCHIVOS_DESCARGAR, "Error de entrada/salida: " + e.getMessage());
        } finally {
            try {
                if (cliente.isConnected()) {
                    cliente.disconnect();
                    Log.e(AsyncTaskArchivosDescargar.ARCHIVOS_DESCARGAR, "Desconectado del servidor.");
                }
            } catch (IOException e) {
                Log.e(AsyncTaskArchivosDescargar.ARCHIVOS_DESCARGAR, "Error de entrada/salida: " + e.getMessage());
            }
        }

        return resultado;
    }
}
