package com.dev.lin.camino;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

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
public class GestionFicheros{
    public static final String archivoUsuarios = "usuarios.dat";

    /*
    public ArrayList<Usuario> leerFicheroUsuarios() {
        FileInputStream fis;
        ObjectInputStream ois = null;
        Object aux;
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        try {
            fis = openFileInput(archivoUsuarios);
            ois = new ObjectInputStream(fis);
            aux = ois.readObject();
            while (aux != null) {
                Usuario us = (Usuario) aux;
                usuarios.add(us);
                aux = ois.readObject();
            }
            ois.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Error: archivo no encontrado.");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.err.println("Error: clase no encontrada.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("Error: problema de entrada-salida");
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            System.err.println("Error: puntero nulo");
            ex.printStackTrace();
        }
        return usuarios;
    }
    */

    public int guardarUsuario(Usuario usuario, Context ctx) {
        int res = 1;
        FileOutputStream fos;

        try {
            fos = ctx.openFileOutput(archivoUsuarios, Context.MODE_PRIVATE);

            ObjectOutputStream oos = new ObjectOutputStream(fos);
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
}
