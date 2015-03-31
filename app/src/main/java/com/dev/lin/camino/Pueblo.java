package com.dev.lin.camino;

/**
 * Created by root on 21/03/15.
 */
public class Pueblo
{
    private
    String nombre;
    double dist_anterior, dist_siguiente;
    boolean comida, hotel,albergue,farmacia,banco_caja,internet;

    public Pueblo(String nom, double da, double ds, boolean com, boolean
            hot, boolean albe, boolean farm, boolean banco, boolean inter)
    {
        nombre= nom;
        dist_anterior = da;
        dist_siguiente = ds;
        comida = com;
        hotel = hot;
        farmacia = farm;
        albergue = albe;
        banco_caja=banco;
        internet = inter;
    }

    public String getNombre(){return nombre;}
    public double getDist_anterior(){return dist_anterior;}
    public double getDist_siguiente(){return dist_siguiente;}
    public boolean getComida(){return comida;}
    public boolean getHotel(){return hotel;}
    public boolean getAlbergue(){return albergue;}
    public boolean getFarmacia(){return farmacia;}
    public boolean getBanco(){return banco_caja;}
    public boolean getInternet(){return internet;}

}
