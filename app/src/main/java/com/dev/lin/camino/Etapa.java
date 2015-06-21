package com.dev.lin.camino;

import com.google.android.gms.maps.model.LatLng;

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
    private static final long serialVersionUID = 2L;
    private int orden;
    private String nombre;
    private double distancia;
    private ArrayList<Integer> indiceParadas;
    private Parada paradaInicio, paradaFin;

    public Etapa(int orden, ArrayList<Integer> indiceParadas) {
        this.orden = orden;
        this.indiceParadas = new ArrayList<Integer>(indiceParadas);

        ArrayList<Parada> listaParadas = new ArrayList<Parada>(this.getListaParadas());
        Parada paradaInicio = listaParadas.get(0);
        Parada paradaFin = listaParadas.get(listaParadas.size() - 1);

        this.nombre = paradaInicio.getNombre() + " - " + paradaFin.getNombre();
        this.distancia = 0;

        Iterator<Parada> itr = listaParadas.iterator();
        Parada parada = itr.next();

        while (itr.hasNext()) {
            parada = itr.next();
            this.distancia += parada.getDistAnterior();
        }
    }

    public Parada getParadaFin(){return paradaFin;}

    public Parada getParadaInicio(){return paradaInicio;}

    public int getOrden() {
        return this.orden;
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getDistancia() {
        return distancia;
    }

    public ArrayList<Parada> getListaParadas() {
        ArrayList<Parada> listaParadas = new ArrayList<Parada>();

        Iterator<Parada> itr = GestionFicherosConfigs.listaParadasCaminoFrances.iterator();
        Parada paradaInicio = GestionFicherosConfigs.listaParadasCaminoFrances.get(this.indiceParadas.get(0) - 1);
        Parada paradaFin = GestionFicherosConfigs.listaParadasCaminoFrances.get(this.indiceParadas.
                get(this.indiceParadas.size() - 2));

        boolean dentro = false;
        Parada parada = null;

        while (itr.hasNext() && !dentro) {
            parada = itr.next();

            if (parada.getNombre().equals(paradaInicio.getNombre())) {
                dentro = true;
            }
        }

        while (itr.hasNext() && dentro) {
            listaParadas.add(parada);

            if (parada.getNombre().equals(paradaFin.getNombre())) {
                dentro = false;
            }

            parada = itr.next();
        }

        return listaParadas;
    }

    public ArrayList<LatLng> getListaCoordsParadas() {
        ArrayList<Parada> listaParadas = new ArrayList<Parada>(this.getListaParadas());
        ArrayList<LatLng> listaLatLng = new ArrayList<LatLng>();
        Parada parada;

        Iterator<Parada> itr = listaParadas.iterator();
        int i = 0;

        while (i < listaParadas.size() - 1) {
            parada = itr.next();

            Iterator<LatLng> itr2 = parada.getListaCoords().iterator();

            while (itr2.hasNext()) {
                listaLatLng.add(itr2.next());
            }

            i++;
        }

        return listaLatLng;
    }

    @Override
    public String toString() {
        String cadena = "Etapa:\nOrden: " + this.orden + "\tNombre: " + this.nombre + "\nDistancia: " +
                this.distancia;

        Iterator<Parada> itr = this.getListaParadas().iterator();
        while (itr.hasNext()) {
            cadena += itr.next().toString();
        }

        return cadena;
    }
}
