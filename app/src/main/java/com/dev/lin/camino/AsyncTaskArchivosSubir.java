package com.dev.lin.camino;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Tarea asíncrona para subir archivos de fotos y coordenadas al servidor mediante FTP
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class AsyncTaskArchivosSubir extends AsyncTask<File, Void, Boolean> {
    private static final String ARCHIVOS_SUBIR = "ArchivosSubir";

    private FTPClient cliente = null;

    public Boolean doInBackground(File... archivos) {
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

        return resultado;
    }
}