package com.dev.lin.camino;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Tarea asíncrona para obtener los archivos en el servidor
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class AsyncTaskListarArchivos extends AsyncTask<Object, Void, ArrayList<String>> {
    private FTPClient cliente = null;
    private FTPFile[] archivos = null;
    private static final String GENERAR_GALERIA = "AsyncTaskListarArchivos";

    public ArrayList<String> doInBackground(Object... params) {
        int reply;
        boolean result;
        ArrayList<String> listado = null;
        BufferedInputStream bis = null;

        cliente = new FTPClient();

        try {
            cliente.connect(ActivityMenuPrincipal.SERVIDOR, ActivityMenuPrincipal.PUERTO);
            cliente.login(ActivityMenuPrincipal.USUARIO, ActivityMenuPrincipal.PASS);
            cliente.changeWorkingDirectory(ActivityMenuPrincipal.DIRECTORIO);

            reply = cliente.getReplyCode();

            if (FTPReply.isPositiveCompletion(reply)) {
                Log.d(AsyncTaskListarArchivos.GENERAR_GALERIA, "Conexión realizada con éxito.");

                listado = new ArrayList<String>();
                cliente.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
                cliente.enterLocalPassiveMode();

                archivos = cliente.listFiles();
                Log.d(AsyncTaskListarArchivos.GENERAR_GALERIA, "Tamaño del listado de la galeria: " +
                        (archivos.length - 2) / 2);

                for (int i = 2; i < archivos.length; i++) {
                    String nombre = archivos[i].getName();
                    nombre = nombre.substring(0, nombre.lastIndexOf("."));
                    Log.d(AsyncTaskListarArchivos.GENERAR_GALERIA, "Nombre: " + nombre);
                    listado.add(nombre);
                    i++;
                }

                cliente.logout();
            } else {
                Log.d(AsyncTaskListarArchivos.GENERAR_GALERIA, "Conexión rechazada por el servidor.");
                cliente.disconnect();
            }
        } catch (IOException e) {
            Log.e(AsyncTaskListarArchivos.GENERAR_GALERIA, "Error de entrada/salida: " + e.getMessage());
        } finally {
            try {
                if (cliente.isConnected()) {
                    cliente.disconnect();
                    Log.e(AsyncTaskListarArchivos.GENERAR_GALERIA, "Desconectado del servidor.");
                }
            } catch (IOException e) {
                Log.e(AsyncTaskListarArchivos.GENERAR_GALERIA, "Error de entrada/salida: " + e.getMessage());
            }
        }

        return listado;
    }
}
