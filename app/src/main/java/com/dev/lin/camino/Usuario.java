package com.dev.lin.camino;

import java.util.Date;
import java.util.List;

/**
 * Created by root on 19/03/15.
 */
public class Usuario
{
    private
    String nombre;
    int peso, altura;
    int añoNacimiento;
    List<Camino> misCaminos;

    public Usuario(int pes, int alt, int fecha,String nom)
    {
        nombre= nom;
        peso = pes;
        altura= alt;
        añoNacimiento = fecha;
    }

    public int getPeso(){return peso;}
    public int getAltura(){return altura;}
    public int getañoNacimiento(){return añoNacimiento;}
    public String getNombre(){return nombre;}

    public void setPeso(int pes){peso = pes;}
    public void setAltura(int alt){altura=alt;}
    public void setañoNacimiento(int fecha){añoNacimiento=fecha;}
    public void addCamino(Camino camino){misCaminos.add(camino);}
    public List<Camino> getCaminos(){return misCaminos;}
}
