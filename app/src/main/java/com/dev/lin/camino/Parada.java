package com.dev.lin.camino;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Pueblos del Camino de Santiago
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class Parada {
    private int orden;
    private String nombre;
    private ArrayList<LatLng> listaCoords;
    private double distAnterior;
    private double distSiguiente;
    private boolean comida;
    private boolean hotel;
    private boolean albergue;
    private boolean farmacia;
    private boolean banco;
    private boolean internet;

    public Parada(int orden, String nombre, ArrayList<LatLng> listaCoords, double distAnterior,
                  double distSiguiente, boolean comida, boolean hotel, boolean albergue,
                  boolean farmacia, boolean banco, boolean internet) {
        this.orden = orden;
        this.nombre = nombre;
        this.listaCoords = new ArrayList<LatLng>(listaCoords);
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

    @Override
    public String toString() {
        return "Parada:\n\tOrden: " + this.orden + "\tNombre: " + this.nombre + "\n\tDistancia Anterior: " + this.distAnterior
                + "\tDistancia Siguiente: " + this.distSiguiente + "\n\tComida: " + this.comida + "\tHotel: " + this.hotel
                + "\tAlbergue: " + this.albergue + "\tFarmacia: " + this.farmacia + "\tBanco: " + this.banco
                + "\tInternet: " + this.internet;
    }
}
