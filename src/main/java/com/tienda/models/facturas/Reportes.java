package com.tienda.models.facturas;

import java.util.Objects;

public class Reportes {

    private int id;
    private String nombre;
    private String contenido;

    public Reportes(int id, String nombre, String contenido) {
        setId(id);
        setNombre(nombre);
        setContenido(contenido);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID debe ser mayor que 0");
        }
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede estar vacío");
        }
        this.nombre = nombre.trim();
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        if (contenido == null || contenido.trim().isEmpty()) {
            throw new IllegalArgumentException("Contenido no puede estar vacío");
        }
        this.contenido = contenido.trim();
    }

    @Override
    public String toString() {
        return "Reportes{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", contenido='" + contenido + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reportes)) return false;
        Reportes reporte = (Reportes) o;
        return id == reporte.id &&
                Objects.equals(nombre, reporte.nombre) &&
                Objects.equals(contenido, reporte.contenido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, contenido);
    }
}
