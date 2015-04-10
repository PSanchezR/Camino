package com.dev.lin.camino;

import java.util.ArrayList;
import java.util.List;

/**
 * Rutas del Camino de Santiago
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class Camino {
    private ArrayList<Etapa> etapasCamino;
    private ArrayList<Etapa> etapasCompletadas;
    private String nombre;
    private String etapaInicial;
    private String etapaFinal;

    public Camino(String nombre, ArrayList<Etapa> etapasCamino, String ini, String fin) {
        this.nombre = nombre;
        this.etapasCamino = etapasCamino;
        this.etapaInicial = ini;
        this.etapaFinal = fin;
    }

    public void setEtapaCompletada(Etapa etapa) {
        this.etapasCompletadas.add(etapa);
    }

    public ArrayList<Etapa> getEtapasCompletadas() {
        return this.etapasCompletadas;
    }

    public ArrayList<Etapa> getEtapasCamino() {
        return this.etapasCamino;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getEtapaInicial(){return  this.etapaInicial;}

    public String getEtapaFinal(){return this.etapaFinal;}
}