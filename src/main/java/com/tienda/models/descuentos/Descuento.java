package com.tienda.models.descuentos;

public class Descuento {
    private int id;
    private String descripcion;
    private double porcentaje;

    public Descuento() {}

    public Descuento(int id, String descripcion, double porcentaje) {
        setId(id);
        setDescripcion(descripcion);
        setPorcentaje(porcentaje);
    }

    public int getId() { return id; }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser > 0");
        this.id = id;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty())
            throw new IllegalArgumentException("Descripción no puede estar vacía");
        this.descripcion = descripcion;
    }

    public double getPorcentaje() { return porcentaje; }

    public void setPorcentaje(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 100)
            throw new IllegalArgumentException("Porcentaje debe estar entre 0 y 100");
        this.porcentaje = porcentaje;
    }
}

