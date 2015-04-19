package com.dev.lin.camino;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Datos de una etapa del Camino de Santiago
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class Etapa implements Serializable {
    private int orden;
    private String nombre;
    private Parada paradaInicio;
    private Parada paradaFin;
    private double KMs;
    private ArrayList<Parada> listaParadas;

    public Etapa(int orden, ArrayList<Parada> listaParadas) {
        this.orden = orden;
        this.listaParadas = new ArrayList<Parada>(listaParadas);
        this.paradaInicio = this.listaParadas.get(0);
        this.paradaFin = this.listaParadas.get(this.listaParadas.size() - 1);
        this.nombre = this.paradaInicio.getNombre() + " - " + this.paradaFin.getNombre();
        this.KMs = 0;

        Iterator<Parada> itr = listaParadas.iterator();
        Parada parada = itr.next();

        while (itr.hasNext()) {
            parada = itr.next();
            this.KMs += parada.getDistAnterior();
        }
    }

    public int getOrden() {
        return this.orden;
    }

    public String getNombre() {
        return this.nombre;
    }

    public ArrayList<Parada> getListaParadas() {
        return this.listaParadas;
    }

    public Parada getParadaInicio() {
        return this.paradaInicio;
    }

    public Parada getParadaFin() {
        return this.paradaFin;
    }

    public String getNombreParadaInicio() {
        return this.paradaInicio.getNombre();
    }

    public String getNombreParadaFin() {
        return this.paradaFin.getNombre();
    }

    public double getKMs() {
        return KMs;
    }
}
