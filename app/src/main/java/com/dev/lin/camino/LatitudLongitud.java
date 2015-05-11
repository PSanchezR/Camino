package com.dev.lin.camino;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Par de latitud/longitud de una coordenada
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class LatitudLongitud implements Serializable {
    private static final long serialVersionUID = 4L;
    private double latitud;
    private double longitud;

    public LatitudLongitud(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud(){
        return this.latitud;
    }

    public double getLongitud(){
        return this.longitud;
    }

    public void setLatitud(double latitud){
        this.latitud = latitud;
    }

    public void setLongitud(double longitud){
        this.longitud = longitud;
    }

    public LatLng getLatLng(){
        return new LatLng(this.latitud,this.longitud);
    }
}
