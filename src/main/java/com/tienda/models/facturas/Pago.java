package com.tienda.models.facturas;

public class Pago {
    private int id;
    private String metodoPago;
    private double monto;
    private String estado;

    public Pago() {}

    public Pago(int id, String metodoPago, double monto, String estado) {
        setId(id);
        setMetodoPago(metodoPago);
        setMonto(monto);
        setEstado(estado);
    }

    public int getId() { return id; }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser > 0");
        this.id = id;
    }

    public String getMetodoPago() { return metodoPago; }

    public void setMetodoPago(String metodoPago) {
        if (metodoPago == null || metodoPago.trim().isEmpty())
            throw new IllegalArgumentException("Método de pago no puede estar vacío");
        this.metodoPago = metodoPago;
    }

    public double getMonto() { return monto; }

    public void setMonto(double monto) {
        if (monto <= 0) throw new IllegalArgumentException("Monto debe ser > 0");
        this.monto = monto;
    }

    public String getEstado() { return estado; }

    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty())
            throw new IllegalArgumentException("Estado no puede estar vacío");
        this.estado = estado;
    }
}
