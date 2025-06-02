package com.tienda.models.inventarios;

import java.util.Objects;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int categoriaId;

    public Producto() {}

    public Producto(int id, String nombre, double precio, int categoriaId) {
        setId(id);
        setNombre(nombre);
        setPrecio(precio);
        setCategoriaId(categoriaId);
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
        this.nombre = nombre.trim();
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) throw new IllegalArgumentException("Precio no puede ser negativo");
        this.precio = precio;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        if (categoriaId <= 0) throw new IllegalArgumentException("CategoriaId debe ser > 0");
        this.categoriaId = categoriaId;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoriaId=" + categoriaId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return id == producto.id &&
                Double.compare(producto.precio, precio) == 0 &&
                categoriaId == producto.categoriaId &&
                Objects.equals(nombre, producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, precio, categoriaId);
    }
}
