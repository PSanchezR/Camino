package com.dev.lin.camino;

import java.util.List;

/**
 * Rutas del Camino de Santiago
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class Camino {
    private List<Etapa> etapasCamino;
    private List<Etapa> etapasCompletadas;
    private String nombre;

    public Camino(String nombre, List<Etapa> etapasCamino) {
        this.nombre = nombre;
        this.etapasCamino = etapasCamino;
    }

    public void setEtapaCompletada(Etapa etapa) {
        this.etapasCompletadas.add(etapa);
    }

    public List<Etapa> getEtapasCompletadas() {
        return this.etapasCompletadas;
    }

    public List<Etapa> getEtapasCamino() {
        return this.etapasCamino;
    }

    public String getNombre() {
        return this.nombre;
    }
}