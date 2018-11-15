package com.example.luis.smarthouse.modelo;

/**
 * Created by luis on 09-12-17.
 */

public class Dispositivos {
    private int id;
    private String nombre;
    private String descripcion;
    private int estado;
    private int tipo;

    public Dispositivos() {
    }

    public Dispositivos(int id, String nombre, String descripcion, int estado, int tipo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return estado+"";
    }
}
