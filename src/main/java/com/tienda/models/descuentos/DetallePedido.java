package com.tienda.models.descuentos;

import java.util.Objects;

public class DetallePedido {

    private int id;
    private int pedidoId;
    private int productoId;
    private int cantidad;

    public DetallePedido() {
        // Constructor por defecto
    }

    public DetallePedido(int id, int pedidoId, int productoId, int cantidad) {
        setId(id);
        setPedidoId(pedidoId);
        setProductoId(productoId);
        setCantidad(cantidad);
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

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        if (pedidoId <= 0) {
            throw new IllegalArgumentException("El ID del pedido debe ser mayor que cero.");
        }
        this.pedidoId = pedidoId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        if (productoId <= 0) {
            throw new IllegalArgumentException("El ID del producto debe ser mayor que cero.");
        }
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "DetallePedido{" +
                "id=" + id +
                ", pedidoId=" + pedidoId +
                ", productoId=" + productoId +
                ", cantidad=" + cantidad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetallePedido)) return false;
        DetallePedido that = (DetallePedido) o;
        return id == that.id &&
                pedidoId == that.pedidoId &&
                productoId == that.productoId &&
                cantidad == that.cantidad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pedidoId, productoId, cantidad);
    }
}
