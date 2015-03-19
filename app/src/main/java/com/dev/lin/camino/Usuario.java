package com.dev.lin.camino;

import java.util.Date;
import java.util.List;

/**
 * Created by root on 19/03/15.
 */
public class Usuario
{
    private
    int peso, altura;
    Date fechaNacimiento;

    public void Usuario(int pes, int alt, Date fecha)
    {
        peso = pes;
        altura= alt;
        fechaNacimiento = fecha;
        List<Camino> misCaminos;
    }

    public int getPeso(){return peso;}
    public int getAltura(){return altura;}
    public Date getFechaNacimiento(){return fechaNacimiento;}

    public void setPeso(int pes){peso = pes;}
    public void setAltura(int alt){altura=alt;}
    public void setFechaNacimiento(Date fecha){fechaNacimiento=fecha;}
}
