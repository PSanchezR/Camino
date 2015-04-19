package com.dev.lin.camino;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Rutas del Camino de Santiago
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class Camino implements Serializable {
    private ArrayList<Etapa> etapasCamino;
    private ArrayList<Etapa> etapasCompletadas;
    private String nombre;
    private String etapaInicial;
    private String etapaFinal;
    private int kmDiarios;

    public Camino(String nombre, ArrayList<Etapa> etapasCamino, int kmDia) {
        this.nombre = nombre;
        this.etapasCamino = etapasCamino;
        this.etapaInicial = etapasCamino.get(0).getParadaInicio();
        this.etapaFinal = etapasCamino.get(0).getParadaFin();
        this.kmDiarios = kmDia;
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

    public String getEtapaInicial() {
        return this.etapaInicial;
    }

    public String getEtapaFinal() {
        return this.etapaFinal;
    }

    public int getKmDiarios() {
        return this.kmDiarios;
    }
}