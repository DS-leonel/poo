package com.tienda.models.descuentos;

import java.time.LocalDate;
import java.util.Objects;

public class Promocion {

    private int id;
    private String nombre;
    private double descuento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Promocion() {
        // Constructor por defecto
    }

    public Promocion(int id, String nombre, double descuento, LocalDate fechaInicio, LocalDate fechaFin) {
        setId(id);
        setNombre(nombre);
        setDescuento(descuento);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        if (descuento < 0 || descuento > 100) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 100.");
        }
        this.descuento = descuento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio == null) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser nula.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        if (fechaFin == null) {
            throw new IllegalArgumentException("La fecha de fin no puede ser nula.");
        }
        if (fechaInicio != null && fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        this.fechaFin = fechaFin;
    }

    public boolean estaActiva(LocalDate fechaActual) {
        return (fechaActual != null &&
                !fechaActual.isBefore(fechaInicio) &&
                !fechaActual.isAfter(fechaFin));
    }

    @Override
    public String toString() {
        return "Promocion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descuento=" + descuento +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Promocion)) return false;
        Promocion that = (Promocion) o;
        return id == that.id &&
                Double.compare(that.descuento, descuento) == 0 &&
                nombre.equals(that.nombre) &&
                fechaInicio.equals(that.fechaInicio) &&
                fechaFin.equals(that.fechaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descuento, fechaInicio, fechaFin);
    }
}
