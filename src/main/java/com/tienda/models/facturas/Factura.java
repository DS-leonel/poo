package com.tienda.models.facturas;

import java.time.LocalDate;
import java.util.Objects;

public class Factura {

    private int id;
    private int clienteId;
    private LocalDate fecha;
    private double total;

    public Factura(int id, int clienteId, LocalDate fecha, double total) {
        setId(id);
        setClienteId(clienteId);
        setFecha(fecha);
        setTotal(total);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser mayor que 0");
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        if (clienteId <= 0) throw new IllegalArgumentException("Cliente ID debe ser mayor que 0");
        this.clienteId = clienteId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        if (fecha == null) throw new IllegalArgumentException("Fecha no puede ser nula");
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        if (total < 0) throw new IllegalArgumentException("Total no puede ser negativo");
        this.total = total;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Factura)) return false;
        Factura factura = (Factura) o;
        return id == factura.id &&
                clienteId == factura.clienteId &&
                Double.compare(factura.total, total) == 0 &&
                Objects.equals(fecha, factura.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clienteId, fecha, total);
    }
}
