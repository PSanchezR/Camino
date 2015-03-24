package com.dev.lin.camino;

/**
 * Created by root on 19/03/15.
 */
public class Etapa
{
    private
        float kilometros;
        String pueblo_Inicio;
        String pueblo_fin;

    public Etapa(float km, String pi, String pf)
    {
        kilometros=km;
        pueblo_Inicio = pi;
        pueblo_fin = pf;
    }
}
