package com.tienda.models.facturas;

public class Pedido {
    private int id;
    private int clienteId;
    private String fecha; // Podrías usar LocalDate si quieres

    public Pedido() {}

    public Pedido(int id, int clienteId, String fecha) {
        setId(id);
        setClienteId(clienteId);
        setFecha(fecha);
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser > 0");
        this.id = id;
    }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) {
        if (clienteId <= 0) throw new IllegalArgumentException("ClienteId debe ser > 0");
        this.clienteId = clienteId;
    }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty())
            throw new IllegalArgumentException("Fecha no puede estar vacía");
        this.fecha = fecha;
    }
}
