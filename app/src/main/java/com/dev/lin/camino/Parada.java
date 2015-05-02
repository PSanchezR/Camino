package com.dev.lin.camino;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Pueblos del Camino de Santiago
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class Parada implements Serializable {
    private int orden;
    private String nombre;
    private ArrayList<LatLng> listaCoords;
    private double latitud;
    private double longitud;
    private double distAnterior;
    private double distSiguiente;
    private boolean comida;
    private boolean hotel;
    private boolean albergue;
    private boolean farmacia;
    private boolean banco;
    private boolean internet;

    public Parada(int orden, String nombre, double latitud, double longitud, double distAnterior,
                  double distSiguiente, boolean comida, boolean hotel, boolean albergue,
                  boolean farmacia, boolean banco, boolean internet) {
        this.orden = orden;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.distAnterior = distAnterior;
        this.distSiguiente = distSiguiente;
        this.comida = comida;
        this.hotel = hotel;
        this.farmacia = farmacia;
        this.albergue = albergue;
        this.banco = banco;
        this.internet = internet;
    }

    public Parada(int orden, String nombre, ArrayList<LatLng> listaCoords, double distAnterior,
                  double distSiguiente, boolean comida, boolean hotel, boolean albergue,
                  boolean farmacia, boolean banco, boolean internet) {
        this.orden = orden;
        this.nombre = nombre;

        Iterator<LatLng> itr = listaCoords.iterator();
        while (itr.hasNext()) {
            this.listaCoords.add(itr.next());
        }

        this.distAnterior = distAnterior;
        this.distSiguiente = distSiguiente;
        this.comida = comida;
        this.hotel = hotel;
        this.farmacia = farmacia;
        this.albergue = albergue;
        this.banco = banco;
        this.internet = internet;
    }

    public int getOrden() {
        return this.orden;
    }

    public String getNombre() {
        return this.nombre;
    }

    public ArrayList<LatLng> getListaCoords() {
        return this.listaCoords;
    }

    public double getLatitud() {
        return this.latitud;
    }

    public double getLongitud() {
        return this.longitud;
    }

    public double getDistAnterior() {
        return this.distAnterior;
    }

    public double getDistSiguiente() {
        return this.distSiguiente;
    }

    public boolean getComida() {
        return this.comida;
    }

    public boolean getHotel() {
        return this.hotel;
    }

    public boolean getAlbergue() {
        return this.albergue;
    }

    public boolean getFarmacia() {
        return this.farmacia;
    }

    public boolean getBanco() {
        return this.banco;
    }

    public boolean getInternet() {
        return this.internet;
    }
}
