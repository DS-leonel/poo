package com.tienda.models.descuentos;

import java.util.Objects;

public class Descuento {

    private int id;
    private String descripcion;
    private double porcentaje;

    public Descuento() {
        // Constructor por defecto
    }

    public Descuento(int id, String descripcion, double porcentaje) {
        setId(id);
        setDescripcion(descripcion);
        setPorcentaje(porcentaje);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
        this.descripcion = descripcion.trim();
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 100.");
        }
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return "Descuento{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", porcentaje=" + porcentaje +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Descuento)) return false;
        Descuento that = (Descuento) o;
        return id == that.id &&
                Double.compare(that.porcentaje, porcentaje) == 0 &&
                descripcion.equals(that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion, porcentaje);
    }
}
