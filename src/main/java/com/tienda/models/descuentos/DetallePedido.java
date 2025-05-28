package com.tienda.models.descuentos;

public class DetallePedido {
    private int id;
    private int pedidoId;
    private int productoId;
    private int cantidad;

    public DetallePedido() {}

    public DetallePedido(int id, int pedidoId, int productoId, int cantidad) {
        setId(id);
        setPedidoId(pedidoId);
        setProductoId(productoId);
        setCantidad(cantidad);
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser > 0");
        this.id = id;
    }

    public int getPedidoId() { return pedidoId; }
    public void setPedidoId(int pedidoId) {
        if (pedidoId <= 0) throw new IllegalArgumentException("PedidoId debe ser > 0");
        this.pedidoId = pedidoId;
    }

    public int getProductoId() { return productoId; }
    public void setProductoId(int productoId) {
        if (productoId <= 0) throw new IllegalArgumentException("ProductoId debe ser > 0");
        this.productoId = productoId;
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        if (cantidad <= 0) throw new IllegalArgumentException("Cantidad debe ser > 0");
        this.cantidad = cantidad;
    }
}
