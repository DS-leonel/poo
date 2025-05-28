package com.tienda.models.inventarios;

public class Sucursal {
    private int id;
    private String nombre;
    private String direccion;

    public Sucursal() {}

    public Sucursal(int id, String nombre, String direccion) {
        setId(id);
        setNombre(nombre);
        setDireccion(direccion);
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

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty())
            throw new IllegalArgumentException("Dirección no puede estar vacía");
        this.direccion = direccion;
    }
}
