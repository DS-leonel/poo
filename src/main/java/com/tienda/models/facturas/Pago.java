package com.tienda.models.facturas;

import java.util.Objects;

public class Pago {

    private int id;
    private String metodoPago;
    private double monto;
    private String estado;

    public Pago(int id, String metodoPago, double monto, String estado) {
        setId(id);
        setMetodoPago(metodoPago);
        setMonto(monto);
        setEstado(estado);
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

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        if (metodoPago == null || metodoPago.trim().isEmpty()) {
            throw new IllegalArgumentException("Método de pago no puede estar vacío");
        }
        this.metodoPago = metodoPago.trim();
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("Monto debe ser mayor que 0");
        }
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("Estado no puede estar vacío");
        }
        this.estado = estado.trim();
    }

    @Override
    public String toString() {
        return "Pago{" +
                "id=" + id +
                ", metodoPago='" + metodoPago + '\'' +
                ", monto=" + monto +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pago)) return false;
        Pago pago = (Pago) o;
        return id == pago.id &&
                Double.compare(pago.monto, monto) == 0 &&
                Objects.equals(metodoPago, pago.metodoPago) &&
                Objects.equals(estado, pago.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, metodoPago, monto, estado);
    }
}
