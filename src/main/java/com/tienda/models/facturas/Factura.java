package com.tienda.models.facturas;

import java.util.Date;

public class Factura {
    private int id;
    private int clienteId;
    private Date fecha;
    private double total;

    public Factura() {}

    public Factura(int id, int clienteId, Date fecha, double total) {
        setId(id);
        setClienteId(clienteId);
        setFecha(fecha);
        setTotal(total);
    }

    public int getId() { return id; }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser > 0");
        this.id = id;
    }

    public int getClienteId() { return clienteId; }

    public void setClienteId(int clienteId) {
        if (clienteId <= 0) throw new IllegalArgumentException("Cliente ID debe ser > 0");
        this.clienteId = clienteId;
    }

    public Date getFecha() { return fecha; }

    public void setFecha(Date fecha) {
        if (fecha == null) throw new IllegalArgumentException("Fecha no puede ser nula");
        this.fecha = fecha;
    }

    public double getTotal() { return total; }

    public void setTotal(double total) {
        if (total < 0) throw new IllegalArgumentException("Total no puede ser negativo");
        this.total = total;
    }
}

