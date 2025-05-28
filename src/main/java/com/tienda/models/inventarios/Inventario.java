package com.tienda.models.inventarios;

public class Inventario {
    private int id;
    private int productoId;
    private int cantidad;

    public Inventario() {}

    public Inventario(int id, int productoId, int cantidad) {
        setId(id);
        setProductoId(productoId);
        setCantidad(cantidad);
    }

    public int getId() { return id; }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser > 0");
        this.id = id;
    }

    public int getProductoId() { return productoId; }

    public void setProductoId(int productoId) {
        if (productoId <= 0) throw new IllegalArgumentException("Producto ID debe ser > 0");
        this.productoId = productoId;
    }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad) {
        if (cantidad < 0) throw new IllegalArgumentException("Cantidad no puede ser negativa");
        this.cantidad = cantidad;
    }
}

