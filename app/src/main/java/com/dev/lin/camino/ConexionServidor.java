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
 * Conexión al servidor de fotos mediante FTP
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class ConexionServidor extends AsyncTask<File, Void, Void> {
    private FTPClient cliente = null;
    private static final String CONEXION_FTP = "ConexionServidor";

    public Void doInBackground(File... archivos) {
        int reply;
        boolean result;
        BufferedInputStream bis = null;

        cliente = new FTPClient();

        try {
            cliente.connect("caminoapp.vhostall.com", 21);
            cliente.login("u489348026", "caminoapp2015");
            cliente.changeWorkingDirectory("fotos");

            reply = cliente.getReplyCode();

            if (FTPReply.isPositiveCompletion(reply)) {
                Log.d(ConexionServidor.CONEXION_FTP, "Conexión realizada con éxito.");
            } else {
                Log.d(ConexionServidor.CONEXION_FTP, "Conexión rechazada por el servidor.");
                cliente.disconnect();
            }

            cliente.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
            cliente.enterLocalPassiveMode();

            for (int i = 0; i < archivos.length; i++) {
                bis = new BufferedInputStream(new FileInputStream(archivos[i]));

                Log.d(ConexionServidor.CONEXION_FTP, "Archivo a subir: " + archivos[i].getName());
                result = cliente.storeFile(archivos[i].getName(), bis);

                if (result) {
                    Log.d(ConexionServidor.CONEXION_FTP, archivos[i].getName() + " subido con éxito al " +
                            "servidor.");
                } else {
                    Log.d(ConexionServidor.CONEXION_FTP, "No se ha podido subir + " + archivos[i].getName() +
                            " al servidor.");
                }

                bis.close();
                Log.e(ConexionServidor.CONEXION_FTP, "Cerrado flujo de entrada.");
            }

            cliente.logout();
        } catch (IOException e) {
            Log.e(ConexionServidor.CONEXION_FTP, "Error de entrada/salida: " + e.getMessage());
        } finally {
            try {
                if (cliente.isConnected()) {
                    cliente.disconnect();
                    Log.e(ConexionServidor.CONEXION_FTP, "Desconectado del servidor.");
                }
            } catch (IOException e) {
                Log.e(ConexionServidor.CONEXION_FTP, "Error de entrada/salida: " + e.getMessage());
            }
        }

        return null;
    }
}