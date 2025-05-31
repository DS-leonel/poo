package com.tienda.models.descuentos;

import java.time.LocalDate;

public class Promocion {
    private int id;
    private String nombre;
    private double descuento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Promocion() {}

    public Promocion(int id, String nombre, double descuento, LocalDate fechaInicio, LocalDate fechaFin) {
        setId(id);
        setNombre(nombre);
        setDescuento(descuento);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
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

    public double getDescuento() { return descuento; }

    public void setDescuento(double descuento) {
        if (descuento < 0 || descuento > 100)
            throw new IllegalArgumentException("Descuento debe estar entre 0 y 100");
        this.descuento = descuento;
    }

    public LocalDate getFechaInicio() { return fechaInicio; }

    public void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio == null) throw new IllegalArgumentException("Fecha de inicio no puede ser nula");
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() { return fechaFin; }

    public void setFechaFin(LocalDate fechaFin) {
        if (fechaFin == null || fechaFin.isBefore(fechaInicio))
            throw new IllegalArgumentException("Fecha de fin no puede ser anterior a la fecha de inicio");
        this.fechaFin = fechaFin;
    }
}