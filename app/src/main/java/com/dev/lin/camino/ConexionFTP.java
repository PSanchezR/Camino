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
 * Conexión FTP
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ConexionFTP extends AsyncTask<File, Void, Void> {
    private FTPClient cliente = null;
    private static final String CONEXION_FTP = "ConexionFTP";

    public Void doInBackground(File... foto) {
        int reply;
        boolean result;
        BufferedInputStream bis = null;

        cliente = new FTPClient();

        try {
            cliente.connect("caminoapp.cloudapp.net", 21);
            cliente.login("caminoapp", "caminoapp");
            cliente.changeWorkingDirectory("fotos");

            reply = cliente.getReplyCode();

            if (FTPReply.isPositiveCompletion(reply)) {
                Log.d(ConexionFTP.CONEXION_FTP, "Conexión realizada con éxito.");
            } else {
                Log.d(ConexionFTP.CONEXION_FTP, "Conexión rechazada por el servidor.");
                cliente.disconnect();
            }

            cliente.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);

            bis = new BufferedInputStream(new FileInputStream(foto[0]));
            cliente.enterLocalPassiveMode();

            Log.d(ConexionFTP.CONEXION_FTP, "Archivo a subir: " + foto[0].getName());
            result = cliente.storeFile(foto[0].getName(), bis);

            if (result) {
                Log.d(ConexionFTP.CONEXION_FTP, "Foto subida con éxito al servidor.");
            } else {
                Log.d(ConexionFTP.CONEXION_FTP, "No se ha podido subir la foto al servidor.");
            }

            cliente.logout();
        } catch (IOException e) {
            Log.e(ConexionFTP.CONEXION_FTP, "Error de entrada/salida: " + e.getMessage());
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                    Log.e(ConexionFTP.CONEXION_FTP, "Cerrado flujo de entrada.");
                }

                if (cliente.isConnected()) {
                    cliente.disconnect();
                    Log.e(ConexionFTP.CONEXION_FTP, "Desconectado del servidor.");
                }
            } catch (IOException e) {
                Log.e(ConexionFTP.CONEXION_FTP, "Error de entrada/salida: " + e.getMessage());
            }
        }

        return null;
    }
}