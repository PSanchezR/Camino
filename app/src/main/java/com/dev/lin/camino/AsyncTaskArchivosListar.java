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
    private static final String ARCHIVOS_LISTAR = "ArchivosListar";

    private FTPClient cliente = null;
    private FTPFile[] archivos = null;

    public ArrayList<String> doInBackground(Object... params) {
        int reply;
        ArrayList<String> listado = null;
        BufferedInputStream bis = null;

        this.cliente = new FTPClient();

        try {
            this.cliente.connect(ActivityMenuPrincipal.SERVIDOR, ActivityMenuPrincipal.PUERTO);
            this.cliente.login(ActivityMenuPrincipal.USUARIO, ActivityMenuPrincipal.PASS);
            this.cliente.changeWorkingDirectory(ActivityMenuPrincipal.DIRECTORIO);

            reply = this.cliente.getReplyCode();

            if (FTPReply.isPositiveCompletion(reply)) {
                Log.d(AsyncTaskArchivosListar.ARCHIVOS_LISTAR, "Conexión realizada con éxito.");

                listado = new ArrayList<String>();
                this.cliente.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
                this.cliente.enterLocalPassiveMode();

                this.archivos = this.cliente.listFiles();
                Log.d(AsyncTaskArchivosListar.ARCHIVOS_LISTAR, "Tamaño del listado de la galeria: " +
                        (this.archivos.length - 2) / 2);

                for (int i = 2; i < this.archivos.length; i++) {
                    String nombre = this.archivos[i].getName();
                    nombre = nombre.substring(0, nombre.lastIndexOf("."));
                    Log.d(AsyncTaskArchivosListar.ARCHIVOS_LISTAR, "Nombre: " + nombre);
                    listado.add(nombre);
                    i++;
                }

                this.cliente.logout();
            } else {
                Log.d(AsyncTaskArchivosListar.ARCHIVOS_LISTAR, "Conexión rechazada por el servidor.");
                this.cliente.disconnect();
            }
        } catch (IOException e) {
            Log.e(AsyncTaskArchivosListar.ARCHIVOS_LISTAR, "Error de entrada/salida: " + e.getMessage());
        } finally {
            try {
                if (this.cliente.isConnected()) {
                    this.cliente.disconnect();
                    Log.e(AsyncTaskArchivosListar.ARCHIVOS_LISTAR, "Desconectado del servidor.");
                }
            } catch (IOException e) {
                Log.e(AsyncTaskArchivosListar.ARCHIVOS_LISTAR, "Error de entrada/salida: " + e.getMessage());
            }
        }

        return listado;
    }
}
