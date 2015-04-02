package com.dev.lin.camino;

import android.content.Context;
import android.util.Log;

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
        FileOutputStream fos;

        try {
            fos = ctx.openFileOutput(GestionFicheros.ARCHIVO_USUARIOS, Context.MODE_APPEND);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(usuario);

            oos.close();

            res = 0;
        } catch (FileNotFoundException e) {
            System.err.println("Error: archivo no encontrado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error: problema de entrada/salida.");
            e.printStackTrace();
        }

        return res;
    }

    public ArrayList<Usuario> recuperarUsuarios(Context ctx) {
        FileInputStream fis;
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        File archivo = ctx.getFileStreamPath(GestionFicheros.ARCHIVO_USUARIOS);

        if (archivo.exists()) {
            try {
                fis = ctx.openFileInput(GestionFicheros.ARCHIVO_USUARIOS);

                ObjectInputStream ois = new ObjectInputStream(fis);
                Object usuario = ois.readObject();

                while (usuario != null) {
                    Log.v(GestionFicheros.TAG, ((Usuario) usuario).toString());
                    usuarios.add((Usuario) usuario);
                    usuario = ois.readObject();
                }

                ois.close();
            } catch (FileNotFoundException e) {
                System.err.println("Error: archivo no encontrado.");
                e.printStackTrace();
            } catch (IOException e) {
                System.err.println("Error: problema de entrada/salida.");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.err.println("Error: no se han podido recuperar los usuarios.");
                e.printStackTrace();
            }
        }

        return usuarios;
    }
}
