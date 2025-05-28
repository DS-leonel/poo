package com.tienda.models.inventarios;

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

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        if (precio < 0) throw new IllegalArgumentException("Precio no puede ser negativo");
        this.precio = precio;
    }

    public int getCategoriaId() { return categoriaId; }
    public void setCategoriaId(int categoriaId) {
        if (categoriaId <= 0) throw new IllegalArgumentException("CategoriaId debe ser > 0");
        this.categoriaId = categoriaId;
    }
}
