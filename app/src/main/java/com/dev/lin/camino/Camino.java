package com.dev.lin.camino;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Rutas del Camino de Santiago
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class Camino implements Serializable {
    private String nombre;
    private Etapa etapaInicio;
    private Etapa etapaFin;
    private double KMs;
    private ArrayList<Etapa> listaEtapas;
    //private int kmDiarios;
    //private ArrayList<Etapa> etapasCompletadas;

    /*
    public Camino(String nombre, ArrayList<Etapa> listaEtapas, int kmDia) {
        this.nombre = nombre;
        this.listaEtapas = listaEtapas;
        this.etapaInicio = listaEtapas.get(0).;
        this.etapaFin = listaEtapas.get(0).getParadaFin();
        this.kmDiarios = kmDia;
    }
    */

    public Camino(String nombre, ArrayList<Etapa> listaEtapas) {
        this.nombre = nombre;
        this.listaEtapas = new ArrayList<Etapa>(listaEtapas);
        this.etapaInicio = this.listaEtapas.get(0);
        this.etapaFin = this.listaEtapas.get(this.listaEtapas.size() - 1);
        this.KMs = 0;

        Iterator<Etapa> itr = listaEtapas.iterator();

        while (itr.hasNext()) {
            Etapa etapa = itr.next();
            this.KMs += etapa.getKMs();
        }
    }

    public String getNombre() {
        return this.nombre;
    }

    public ArrayList<Etapa> getListaEtapas() {
        return this.listaEtapas;
    }

    public Etapa getEtapaInicio() {
        return this.etapaInicio;
    }

    public Etapa getEtapaFin() {
        return this.etapaFin;
    }

    public String getNombreEtapaInicio() {
        return this.etapaInicio.getNombre();
    }

    public String getNombreEtapaFin() {
        return this.etapaFin.getNombre();
    }

    public double getKMs() {
        return this.KMs;
    }

        /*
    public void setEtapaCompletada(Etapa etapa) {
        this.etapasCompletadas.add(etapa);
    }

    public ArrayList<Etapa> getEtapasCompletadas() {
        return this.etapasCompletadas;
    }
    */

    /*
    public int getKmDiarios() {
        return this.kmDiarios;
    }
    */
}