package com.dev.lin.camino;

import java.io.Serializable;

/**
 * Datos de una etapa del Camino de Santiago
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class Etapa implements Serializable {
    private String paradaInicio;
    private String paradaFin;
    private double KMs;

    public Etapa(double KMs, String paradaInicio, String paradaFin) {
        this.KMs = KMs;
        this.paradaInicio = paradaInicio;
        this.paradaFin = paradaFin;
    }

    public String getParadaInicio() {
        return paradaInicio;
    }

    public String getParadaFin() {
        return paradaFin;
    }

    public double getKMs() {
        return KMs;
    }
}
