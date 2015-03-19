package com.dev.lin.camino;

import java.util.List;

/**
 * Created by root on 19/03/15.
 */
public class Camino
{
    String nombre;
    List<Etapa_Seleccionada> etapasCamino;
    List<Etapa_Seleccionada> etapasCompletadas;

    public void Camino(String nom, List<Etapa_Seleccionada>etapasCam)
    {
        nombre = nom;
        etapasCamino = etapasCam;
    }
    
}
