package com.tienda.models;

public class Cliente {
    private int id;
    private String nombre;

    public Cliente() {}

    public Cliente(int id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public int getId() { return id; }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser > 0");
        this.id = id;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("Nombre no puede estar vacío");
        this.nombre = nombre;
    }
}
