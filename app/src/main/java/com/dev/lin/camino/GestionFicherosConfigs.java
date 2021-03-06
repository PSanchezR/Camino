package com.dev.lin.camino;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

/**
 * Gestión de los ficheros con los datos de usuario y obtención de configuraciones
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */

public class GestionFicherosConfigs {
    private static final String ESCRIBIR_USUARIOS = "EscribirUsuarios";
    private static final String LEER_USUARIOS = "LeerUsuarios";
    private static final String DATOS_PARADA = "DatosParada";

    public static ArrayList<Parada> listaParadasCaminoFrances = new ArrayList<Parada>();

    public static int escribirUsuarios(Usuario usuario, Context ctx) {
        int res = 1;
        String filename = usuario.getNombre() + ".dat";
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = ctx.openFileOutput(filename, Context.MODE_PRIVATE);

            oos = new ObjectOutputStream(fos);
            Log.v(GestionFicherosConfigs.ESCRIBIR_USUARIOS, "Se ha creado un archivo nuevo.");

            oos.writeObject(usuario);
            oos.flush();

            res = 0;
        } catch (FileNotFoundException e) {
            Log.e(GestionFicherosConfigs.ESCRIBIR_USUARIOS, "Error: archivo no encontrado.\n\t" +
                    e.getMessage());
        } catch (IOException e) {
            Log.e(GestionFicherosConfigs.ESCRIBIR_USUARIOS, "Error: problema de entrada salida.\n\t" +
                    e.getMessage());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                } else if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                Log.e(GestionFicherosConfigs.ESCRIBIR_USUARIOS, "Error: fallo al cerrar el flujo de " +
                        "escritura.\n\t" + e.getMessage());
            }
        }

        return res;
    }

    public static ArrayList<Usuario> leerUsuarios(Context ctx) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<String> archivos = new ArrayList<String>(Arrays.asList(ctx.fileList()));
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        Iterator<String> itr = archivos.iterator();

        while (itr.hasNext()) {
            String archivo = itr.next();
            File acceso = ctx.getFileStreamPath(archivo);

            if (acceso.isFile()) {
                try {
                    fis = ctx.openFileInput(archivo);
                    ois = new ObjectInputStream(fis);

                    while (true) {
                        Usuario usuario = (Usuario) ois.readObject();
                        //Log.d(GestionFicherosConfigs.LEER_USUARIOS, usuario.toString());
                        usuarios.add(usuario);
                    }
                } catch (EOFException e) {
                    Log.d(GestionFicherosConfigs.LEER_USUARIOS, "Fin de archivo.\n\t" + e.getMessage());
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    Log.e(GestionFicherosConfigs.LEER_USUARIOS, "Error: archivo no encontrado.\n\t"
                            + e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e(GestionFicherosConfigs.LEER_USUARIOS, "Error: problema de entrada/salida.\n\t"
                            + e.getMessage());
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    Log.e(GestionFicherosConfigs.LEER_USUARIOS, "Error: no se han podido recuperar los " +
                            "usuarios.\n\t" + e.getMessage());
                    e.printStackTrace();
                } finally {
                    try {
                        if (fis != null) {
                            fis.close();
                        } else if (ois != null) {
                            ois.close();
                        }
                    } catch (IOException e) {
                        Log.e(GestionFicherosConfigs.LEER_USUARIOS, "Error: fallo al cerrar el flujo de " +
                                "lectura.\n\t" + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }

        return usuarios;
    }

    public static void generarParadasListadoCaminoFrances(String archivoCamino, Context ctx) {
        Iterator<Parada> itr = GestionFicherosConfigs.parsearArchivoCamino(archivoCamino, ctx).iterator();
        Parada parada;

        while (itr.hasNext()) {
            parada = itr.next();
            GestionFicherosConfigs.listaParadasCaminoFrances.add(parada);
        }
    }

    public static ArrayList<Parada> parsearArchivoCamino(String archivoCamino, Context ctx) {
        ArrayList<Parada> paradas = new ArrayList<Parada>();

        int orden = -1;
        String nombre = null;
        ArrayList<LatLng> listaCoords = new ArrayList<LatLng>();
        double distAnterior = -1.0;
        double distSiguiente = -1.0;
        boolean comida = false;
        boolean hotel = false;
        boolean albergue = false;
        boolean farmacia = false;
        boolean banco = false;
        boolean internet = false;

        int eventType;
        XmlPullParserFactory pullParserFactory;
        String etiqueta;

        try {
            InputStream istr = ctx.getAssets().open(archivoCamino);

            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(istr, null);

            eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                etiqueta = null;

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        etiqueta = parser.getName();

                        switch (etiqueta) {
                            case "orden":
                                orden = Integer.parseInt(parser.nextText());
                                break;
                            case "nombre":
                                nombre = parser.nextText();
                                break;
                            case "coords":
                                String[] parts = parser.nextText().split(",");
                                LatLng coord = new LatLng(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]));
                                listaCoords.add(coord);
                                break;
                            case "distAnterior":
                                distAnterior = Float.parseFloat(parser.nextText());
                                break;
                            case "distSiguiente":
                                distSiguiente = Float.parseFloat(parser.nextText());
                                break;
                            case "comida":
                                comida = Boolean.parseBoolean(parser.nextText());
                                break;
                            case "hotel":
                                hotel = Boolean.parseBoolean(parser.nextText());
                                break;
                            case "albergue":
                                albergue = Boolean.parseBoolean(parser.nextText());
                                break;
                            case "farmacia":
                                farmacia = Boolean.parseBoolean(parser.nextText());
                                break;
                            case "banco":
                                banco = Boolean.parseBoolean(parser.nextText());
                                break;
                            case "internet":
                                internet = Boolean.parseBoolean(parser.nextText());
                                break;
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        Parada parada = new Parada(orden, nombre, listaCoords, distAnterior, distSiguiente,
                                comida, hotel, albergue, farmacia, banco, internet);
                        paradas.add(parada);
                        listaCoords.clear();
                        break;
                }

                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            Log.e(GestionFicherosConfigs.DATOS_PARADA, "Error en el procesador del archivo XML: "
                    + e.getMessage());
        } catch (IOException e) {
            Log.e(GestionFicherosConfigs.DATOS_PARADA, "Error de entrada/salida en el archivo XML: "
                    + e.getMessage());
        }

        return paradas;
    }

    public static boolean comprobarConexion(Context ctx) {
        boolean conexion = false;

        ConnectivityManager gestor = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] redes = gestor.getAllNetworkInfo();

        for (int i = 0; i < redes.length; i++) {
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                conexion = true;
            }
        }

        return conexion;
    }

    public static String getFechaHoraActual() {
        Date fecha = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("ddMMyy-hhmmss");
        return formateador.format(fecha);
    }

    public static String boolString(boolean valor) {
        return valor ? "Sí" : "No";
    }
}