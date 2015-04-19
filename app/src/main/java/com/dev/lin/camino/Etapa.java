package com.dev.lin.camino;

import java.io.Serializable;

/**
 * Datos de una etapa del Camino de Santiago
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class Etapa implements Serializable {
    private String puntoInicio;
    private String puntoFin;
    private double KMs;

    public Etapa(double KMs, String puntoInicio, String puntoFin) {
        this.KMs = KMs;
        this.puntoInicio = puntoInicio;
        this.puntoFin = puntoFin;
    }

    public String getPuntoInicio() {
        return puntoInicio;
    }

    public String getPuntoFin() {
        return puntoFin;
    }

    public double getKMs() {
        return KMs;
    }
}
