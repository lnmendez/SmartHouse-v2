package com.example.luis.smarthouse.modelo;

/**
 * Created by luis on 10-12-17.
 */

public class Desarrolladores {
    private String nombre;
    private String cargo;
    private String correo;
    private String numero;


    public Desarrolladores() {
    }

    public Desarrolladores(String nombre, String cargo, String correo, String numero) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.correo = correo;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
