package com.dev.lin.camino;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Usuario de la aplicación
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class Usuario implements Serializable {
    public static final int NADA_DEPORTISTA = 0;
    public static final int POCO_DEPORTISTA = 1;
    public static final int DEPORTISTA_AMATEUR = 2;
    public static final int DEPORTISTA_PROFESIONAL = 3;
    private static final long serialVersionUID = 0L;
    private String nombre;
    private int altura;
    private int peso;
    private int complexion;
    private int anioNacimiento;
    private double kmMaximos;
    private ArrayList<Camino> misCaminos;
    private Camino camino_actual;


    public Usuario() {
    }

    public Usuario(String nombre, int altura, int peso, int complexion, int anioNacimiento) {
        super();
        this.nombre = nombre;
        this.altura = altura;
        this.peso = peso;
        this.complexion = complexion;
        this.anioNacimiento = anioNacimiento;
        this.camino_actual = null;
        this.misCaminos = null;
        calcularKmMaximos();
    }

    public double calcularKmMaximos()
    {
        double kmBase = 0.0;
        double multiplicador= 1;
        Date fecha = new Date();

        Double imc = this.peso/Math.pow(this.altura/100,2);




        //Creando base según la complexión
        if(this.complexion == 0)
        {
            kmBase=15;
        }else if(this.complexion == 1)
        {
            kmBase=20;
        }else if(this.complexion == 2)
        {
            kmBase=25;
        }else if(this.complexion == 3)
        {
            kmBase=30;
        }

        //Añadiendo imc a la ponderación

        if(imc < 16)
        {
            multiplicador = 1;
        }else if(imc >=16 && imc < 25)
        {
            multiplicador+=0.4;
        }
        else if(imc >=25 && imc < 30)
        {
            multiplicador += 0.2;
        }
        else if(imc > 30 && imc <35)
        {
            multiplicador=1;
        }
        else
        {
            multiplicador-=0.2;
        }

        //Añadiendo la edad a la ponderación
        multiplicador += (1/( fecha.getYear() - this.anioNacimiento));

       this.kmMaximos = multiplicador*kmBase;

        return this.kmMaximos;

    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAltura() {
        return this.altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getComplexion() {
        return this.complexion;
    }

    public void setComplexion(int complexion) {
        this.complexion = complexion;
    }

    public int getAnioNacimiento() {
        return this.anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public ArrayList<Camino> getMisCaminos() {
        return this.misCaminos;
    }

    public void addCamino(Camino camino) {
        this.misCaminos.add(camino);
    }

    public void removeCamino(Camino camino) {
        this.misCaminos.remove(camino);
    }

    public double getKmMaximos(){return this.kmMaximos;}

    public void setKmMaximos(double km){this.kmMaximos = km;}

    @Override
    public String toString() {
        return "Usuario -> nombre:" + this.nombre + ", altura:" + this.altura + ", peso:" + this.peso + ", complexion:" + this.complexion +
                ", año de nacimiento:" + this.anioNacimiento+ ",distancia máxima "+this.kmMaximos;
    }

}
