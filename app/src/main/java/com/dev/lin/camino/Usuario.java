package com.dev.lin.camino;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable{

    private static final long serialVersionUID = 636161369614615L;
    private String nombre;
    private int altura;
    private int peso;
    private int complexion;
    private int anioNacimiento;
    private ArrayList<Camino> misCaminos;
    private Camino camino_actual;

    public static final int NADA_DEPORTISTA = 0;
    public static final int POCO_DEPORTISTA = 1;
    public static final int DEPORTISTA_AMATEUR = 2;
    public static final int DEPORTISTA_PROFESIONAL = 3;


    public Usuario(){
    }

    public Usuario(String nombre, int altura, int peso, int complexion, int anioNacimiento){
        super();
        this.nombre = nombre;
        this.altura = altura;
        this.peso = peso;
        this.complexion = complexion;
        this.anioNacimiento = anioNacimiento;
        this.camino_actual = null;
        this.misCaminos = null;

    }

    public String getNombre(){
        return this.nombre;
    }

    public int getAltura(){
        return this.altura;
    }

    public int getPeso(){
        return this.peso;
    }

    public int getComplexion(){
        return this.complexion;
    }

    public int getAnioNacimiento(){
        return this.anioNacimiento;
    }

    public ArrayList<Camino> getMisCaminos(){
        return this.misCaminos;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setAltura(int altura){
        this.altura = altura;
    }

    public void setPeso(int peso){
        this.peso = peso;
    }

    public void setComplexion(int complexion){
        this.complexion = complexion;
    }

    public void setAnioNacimiento(int anioNacimiento){
        this.anioNacimiento = anioNacimiento;
    }

    public void addCamino(Camino camino){
        this.misCaminos.add(camino);
    }

    public void removeCamino(Camino camino)
    {
        for(int i = 0; i < misCaminos.size();i++)
        {
            if(misCaminos.get(i).getNombre().compareTo(camino.getNombre())==0)
            {
                misCaminos.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        return "Usuario -> nombre:" + this.nombre + ", altura:" + this.altura + ", peso:" + this.peso + ", complexion:" +
                this.complexion + ", año de nacimiento: " + this.anioNacimiento + ", caminos: " + this.misCaminos;
    }

}
