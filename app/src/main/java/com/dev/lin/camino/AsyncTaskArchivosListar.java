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
public class AsyncTaskArchivosListar extends AsyncTask<Object, Void, ArrayList<String>> {
    private FTPClient cliente = null;
    private FTPFile[] archivos = null;
    private static final String ARCHIVOS_LISTAR = "ArchivosListar";

    public ArrayList<String> doInBackground(Object... params) {
        int reply;
        ArrayList<String> listado = null;
        BufferedInputStream bis = null;

        cliente = new FTPClient();

        try {
            cliente.connect(ActivityMenuPrincipal.SERVIDOR, ActivityMenuPrincipal.PUERTO);
            cliente.login(ActivityMenuPrincipal.USUARIO, ActivityMenuPrincipal.PASS);
            cliente.changeWorkingDirectory(ActivityMenuPrincipal.DIRECTORIO);

            reply = cliente.getReplyCode();

            if (FTPReply.isPositiveCompletion(reply)) {
                Log.d(AsyncTaskArchivosListar.ARCHIVOS_LISTAR, "Conexión realizada con éxito.");

                listado = new ArrayList<String>();
                cliente.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
                cliente.enterLocalPassiveMode();

                archivos = cliente.listFiles();
                Log.d(AsyncTaskArchivosListar.ARCHIVOS_LISTAR, "Tamaño del listado de la galeria: " +
                        (archivos.length - 2) / 2);

                for (int i = 2; i < archivos.length; i++) {
                    String nombre = archivos[i].getName();
                    nombre = nombre.substring(0, nombre.lastIndexOf("."));
                    Log.d(AsyncTaskArchivosListar.ARCHIVOS_LISTAR, "Nombre: " + nombre);
                    listado.add(nombre);
                    i++;
                }

                cliente.logout();
            } else {
                Log.d(AsyncTaskArchivosListar.ARCHIVOS_LISTAR, "Conexión rechazada por el servidor.");
                cliente.disconnect();
            }
        } catch (IOException e) {
            Log.e(AsyncTaskArchivosListar.ARCHIVOS_LISTAR, "Error de entrada/salida: " + e.getMessage());
        } finally {
            try {
                if (cliente.isConnected()) {
                    cliente.disconnect();
                    Log.e(AsyncTaskArchivosListar.ARCHIVOS_LISTAR, "Desconectado del servidor.");
                }
            } catch (IOException e) {
                Log.e(AsyncTaskArchivosListar.ARCHIVOS_LISTAR, "Error de entrada/salida: " + e.getMessage());
            }
        }

        return listado;
    }
}
