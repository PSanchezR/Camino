package com.dev.lin.camino;

import java.io.Serializable;

/**
 * Datos de una etapa del Camino de Santiago
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class Etapa implements Serializable {
    private String puebloInicio;
    private String puebloFin;
    private double kms;

    public Etapa(double kms, String puebloInicio, String puebloFin) {
        this.kms = kms;
        this.puebloInicio = puebloInicio;
        this.puebloFin = puebloFin;
    }

    public String getPuebloInicio() {
        return puebloInicio;
    }

    public String getPuebloFin() {
        return puebloFin;
    }

    public double getKms() {
        return kms;
    }
}
