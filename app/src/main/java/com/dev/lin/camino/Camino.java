package com.dev.lin.camino;

import java.util.List;

/**
 * Created by root on 19/03/15.
 */
public class Camino
{
    private
    String nombre;
    List<Etapa> etapasCamino;
    List<Etapa> etapasCompletadas;

    public Camino(String nom, List<Etapa>etapasCam)
    {
        nombre = nom;
        etapasCamino = etapasCam;
    }

    public void setEtapaCompletada(Etapa etapa)
    {
        etapasCompletadas.add(etapa);
    }

    public List<Etapa> getEtapasCompletadas(){return this.etapasCompletadas;}
    public List<Etapa> getEtapasCamino(){return this.etapasCamino;}
    public String getNombre(){return this.nombre;}

}
