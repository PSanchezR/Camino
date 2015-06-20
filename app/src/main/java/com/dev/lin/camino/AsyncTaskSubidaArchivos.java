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
public class AsyncTaskSubidaArchivos extends AsyncTask<File, Void, Void> {
    private FTPClient cliente = null;
    private static final String SUBIDA_ARCHIVOS = "AsyncTaskSubidaArchivos";

    public Void doInBackground(File... archivos) {
        int reply;
        boolean result;
        BufferedInputStream bis = null;

        cliente = new FTPClient();

        try {
            cliente.connect(ActivityMenuPrincipal.SERVIDOR, ActivityMenuPrincipal.PUERTO);
            cliente.login(ActivityMenuPrincipal.USUARIO, ActivityMenuPrincipal.PASS);
            cliente.changeWorkingDirectory(ActivityMenuPrincipal.DIRECTORIO);

            reply = cliente.getReplyCode();

            if (FTPReply.isPositiveCompletion(reply)) {
                Log.d(AsyncTaskSubidaArchivos.SUBIDA_ARCHIVOS, "Conexión realizada con éxito.");

                cliente.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
                cliente.enterLocalPassiveMode();

                for (int i = 0; i < archivos.length; i++) {
                    bis = new BufferedInputStream(new FileInputStream(archivos[i]));

                    Log.d(AsyncTaskSubidaArchivos.SUBIDA_ARCHIVOS, "Archivo a subir: " + archivos[i].getName());
                    result = cliente.storeFile(archivos[i].getName(), bis);

                    if (result) {
                        Log.d(AsyncTaskSubidaArchivos.SUBIDA_ARCHIVOS, archivos[i].getName() +
                                " subido con éxito al servidor.");
                    } else {
                        Log.d(AsyncTaskSubidaArchivos.SUBIDA_ARCHIVOS, "No se ha podido subir + "
                                + archivos[i].getName() + " al servidor.");
                    }

                    bis.close();
                    Log.e(AsyncTaskSubidaArchivos.SUBIDA_ARCHIVOS, "Cerrado flujo de entrada.");
                }

                cliente.logout();
            } else {
                Log.d(AsyncTaskSubidaArchivos.SUBIDA_ARCHIVOS, "Conexión rechazada por el servidor.");
                cliente.disconnect();
            }
        } catch (IOException e) {
            Log.e(AsyncTaskSubidaArchivos.SUBIDA_ARCHIVOS, "Error de entrada/salida: " + e.getMessage());
        } finally {
            try {
                if (cliente.isConnected()) {
                    cliente.disconnect();
                    Log.e(AsyncTaskSubidaArchivos.SUBIDA_ARCHIVOS, "Desconectado del servidor.");
                }
            } catch (IOException e) {
                Log.e(AsyncTaskSubidaArchivos.SUBIDA_ARCHIVOS, "Error de entrada/salida: " + e.getMessage());
            }
        }

        return null;
    }
}