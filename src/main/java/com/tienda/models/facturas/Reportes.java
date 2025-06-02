package com.tienda.models.facturas;

public class Reportes {
    private int id;
    private String nombre;
    private String contenido;

    public Reportes() {} // Constructor corregido

    public Reportes(int id, String nombre, String contenido) { // Constructor corregido
        setId(id);
        setNombre(nombre);
        setContenido(contenido);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser > 0");
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("Nombre no puede estar vacío");
        this.nombre = nombre;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        if (contenido == null || contenido.trim().isEmpty())
            throw new IllegalArgumentException("Contenido no puede estar vacío");
        this.contenido = contenido;
    }
}