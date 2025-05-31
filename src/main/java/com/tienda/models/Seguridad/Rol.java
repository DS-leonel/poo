package com.tienda.models.Seguridad;

public class Rol {
    private int id;
    private String nombre;

    public Rol() {
    }

    public Rol(int id, String nombre) {
        setId(id);
        setNombre(nombre);
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID del rol debe ser > 0");
        }
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre del rol no puede estar vacío");
        }
        this.nombre = nombre;
    }

}

