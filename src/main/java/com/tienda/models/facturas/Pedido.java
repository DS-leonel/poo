package com.tienda.models.facturas;

import java.time.LocalDate;
import java.util.Objects;

public class Pedido {

    private int id;
    private int clienteId;
    private LocalDate fecha;

    public Pedido(int id, int clienteId, LocalDate fecha) {
        setId(id);
        setClienteId(clienteId);
        setFecha(fecha);
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

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        if (clienteId <= 0) {
            throw new IllegalArgumentException("ClienteId debe ser mayor que 0");
        }
        this.clienteId = clienteId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("Fecha no puede ser nula");
        }
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", fecha=" + fecha +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return id == pedido.id &&
                clienteId == pedido.clienteId &&
                Objects.equals(fecha, pedido.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clienteId, fecha);
    }
}
