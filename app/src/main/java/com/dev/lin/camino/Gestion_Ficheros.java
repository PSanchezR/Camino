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
 * Created by root on 25/03/15.
 */
public class Gestion_Ficheros extends Activity
{
    private String archivo_usuarios="usuarios.dat";

    public ArrayList<Usuario> leerFicheroUsuarios()
    {
        FileInputStream fis;
        ObjectInputStream ois = null;
        Object aux;
        ArrayList<Usuario> usuarios=  new ArrayList<Usuario>();
        try
        {
            fis = openFileInput(archivo_usuarios);
            ois = new ObjectInputStream(fis);
            aux = ois.readObject();
            while(aux!=null)
            {
                Usuario us = (Usuario)aux;
                usuarios.add(us);
                aux = ois.readObject();

            }
            ois.close();
        }
        catch(FileNotFoundException ex){
            System.err.println("Error: archivo no encontrado.");
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex)
        {
            System.err.println("Error: Clase no encontrada.");
            ex.printStackTrace();
        }catch(IOException ex)
        {
            System.err.println("Error: Problema de entrada-salida");
            ex.printStackTrace();
        }catch (NullPointerException ex)
        {
            System.err.println("Error: Puntero nulo");
            ex.printStackTrace();
        }
        return usuarios;
    }


    public void guardarUsuarios(Usuario usuario, ArrayList<Usuario> usuarios) {
        FileOutputStream fos;
        usuarios.add(usuario);
        try {
            fos = openFileOutput(archivo_usuarios, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for(int i = 0; i < usuarios.size();i++)
            {
                oos.writeObject(usuarios.get(i));
            }
            oos.close();


            Toast.makeText(this, "Usuario guardado correctamente.", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            System.err.println("Error: archivo no encontrado.");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.err.println("Error: problema de entrada/salida.");
            e.printStackTrace();
        }
    }
}
