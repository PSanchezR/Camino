package com.dev.lin.camino;

import android.content.Context;
import android.util.Log;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Gestión de los ficheros con los datos de usuario
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class GestionFicheros {
    public static final String ARCHIVO_USUARIOS = "usuarios.dat";
    private static final String TAG = "GestionFicheros";

    public int guardarUsuario(Usuario usuario, Context ctx) {
        int res = 1;
        FileOutputStream fos = null;
        File archivo = ctx.getFileStreamPath(GestionFicheros.ARCHIVO_USUARIOS);

        try {
            fos = ctx.openFileOutput(GestionFicheros.ARCHIVO_USUARIOS, Context.MODE_APPEND);

            /*if (archivo.exists()) {
                FlujoSalidaObjetoNoCabecera oos = new FlujoSalidaObjetoNoCabecera(fos);
                oos.writeObject(usuario);
                oos.close();
            } else {*/
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(usuario);
                oos.close();
            //}

            res = 0;
        } catch (FileNotFoundException e) {
            System.err.println("Error: archivo no encontrado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error: problema de entrada/salida.");
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                System.err.println("Error: fallo al cerrar el flujo de escritura.");
                e.printStackTrace();
            }
        }

        return res;
    }

    public ArrayList<Usuario> recuperarUsuarios(Context ctx) {
        FileInputStream fis = null;
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        File archivo = ctx.getFileStreamPath(GestionFicheros.ARCHIVO_USUARIOS);

        if (archivo.exists()) {
            try {
                fis = ctx.openFileInput(GestionFicheros.ARCHIVO_USUARIOS);

                ObjectInputStream ois = new ObjectInputStream(fis);

                while (true) {
                    Object usuario = ois.readObject();
                    Log.v(GestionFicheros.TAG, ((Usuario) usuario).toString());
                    usuarios.add((Usuario) usuario);
                }
            } catch (EOFException e) {
                System.err.println("Salida de archivo.");
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                System.err.println("Error: archivo no encontrado.");
                e.printStackTrace();
            } catch (IOException e) {
                System.err.println("Error: problema de entrada/salida.");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.err.println("Error: no se han podido recuperar los usuarios.");
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null)
                        fis.close();
                } catch (IOException e) {
                    System.err.println("Error: fallo al cerrar el flujo de lectura.");
                    e.printStackTrace();
                }
            }
        }

        return usuarios;
    }
}
