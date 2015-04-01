package com.dev.lin.camino;

/**
 * Datos de una etapa del Camino de Santiago
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class Etapa {
    private String puebloInicio;
    private String puebloFin;
    private float kms;

    public Etapa(float kms, String puebloInicio, String puebloFin) {
        this.kms = kms;
        this.puebloInicio = puebloInicio;
        this.puebloFin = puebloFin;
    }
}
