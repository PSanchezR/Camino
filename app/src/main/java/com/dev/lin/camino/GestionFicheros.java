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
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Gestión de los ficheros con los datos de usuario
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class GestionFicheros {
    public static final String ARCHIVO_USUARIOS = "usuarios.dat";
    private static final String ESCRIBIR_USUARIOS = "EscribirUsuarios";
    private static final String LEER_USUARIOS = "LeerUsuarios";

    public int escribirUsuarios(Usuario usuario, Context ctx) {
        int res = 1;
        File archivo = ctx.getFileStreamPath(GestionFicheros.ARCHIVO_USUARIOS);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = ctx.openFileOutput(GestionFicheros.ARCHIVO_USUARIOS, Context.MODE_APPEND);

            if (archivo.length() == 0) {
                oos = new ObjectOutputStream(fos);
                Log.v(GestionFicheros.ESCRIBIR_USUARIOS, "Se ha creado un archivo nuevo.");
            } else {
                oos = new AppendableObjectOutputStream(fos);
                Log.v(GestionFicheros.ESCRIBIR_USUARIOS, "No se ha creado un archivo nuevo.");
            }

            oos.writeObject(usuario);
            oos.flush();

            res = 0;
        } catch (FileNotFoundException e) {
            Log.e(GestionFicheros.ESCRIBIR_USUARIOS, "Error: archivo no encontrado.\n\t" + e.getMessage());
        } catch (IOException e) {
            Log.e(GestionFicheros.ESCRIBIR_USUARIOS, "Error: problema de entrada salida.\n\t" + e.getMessage());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                } else if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                Log.e(GestionFicheros.ESCRIBIR_USUARIOS, "Error: fallo al cerrar el flujo de escritura.\n\t" + e.getMessage());
            }
        }

        return res;
    }

    public ArrayList<Usuario> leerUsuarios(Context ctx) {
        File archivo = ctx.getFileStreamPath(GestionFicheros.ARCHIVO_USUARIOS);
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        if (archivo.exists()) {
            try {
                fis = ctx.openFileInput(GestionFicheros.ARCHIVO_USUARIOS);
                ois = new ObjectInputStream(fis);

                while (true) {
                    Usuario usuario = (Usuario) ois.readObject();
                    Log.d(GestionFicheros.LEER_USUARIOS, usuario.toString());
                    usuarios.add(usuario);
                }
            } catch (EOFException e) {
                Log.d(GestionFicheros.LEER_USUARIOS, "Fin de archivo.\n\t" + e.getMessage());
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                Log.e(GestionFicheros.LEER_USUARIOS, "Error: archivo no encontrado.\n\t" + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(GestionFicheros.LEER_USUARIOS, "Error: problema de entrada/salida.\n\t" + e.getMessage());
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                Log.e(GestionFicheros.LEER_USUARIOS, "Error: no se han podido recuperar los usuarios.\n\t" + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    } else if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    Log.e(GestionFicheros.LEER_USUARIOS, "Error: fallo al cerrar el flujo de lectura.\n\t" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        return usuarios;
    }

    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }
}