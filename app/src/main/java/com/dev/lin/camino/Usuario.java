package com.dev.lin.camino;

import java.io.Serializable;
import java.util.ArrayList;

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
    private static final long serialVersionUID = 636161369614615L;
    private String nombre;
    private int altura;
    private int peso;
    private int complexion;
    private int anioNacimiento;
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
        this.misCaminos.clear();
    }

    @Override
    public String toString() {
        return "Usuario -> nombre:" + this.nombre + ", altura:" + this.altura + ", peso:" + this.peso + ", complexion:" + this.complexion +
                ", año de nacimiento:" + this.anioNacimiento;
    }

}
