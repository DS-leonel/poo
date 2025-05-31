package com.tienda.models.inventarios;

import java.time.LocalDate;

public class Envio {
    private int id;
    private String direccion;
    private LocalDate fechaEnvio;
    private String estado;

    public Envio() {}

    public Envio(int id, String direccion, LocalDate fechaEnvio, String estado) {
        setId(id);
        setDireccion(direccion);
        setFechaEnvio(fechaEnvio);
        setEstado(estado);
    }

    public int getId() { return id; }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser > 0");
        this.id = id;
    }

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty())
            throw new IllegalArgumentException("Dirección no puede estar vacía");
        this.direccion = direccion;
    }

    public LocalDate getFechaEnvio() { return fechaEnvio; }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        if (fechaEnvio == null) throw new IllegalArgumentException("Fecha de envío no puede ser nula");
        this.fechaEnvio = fechaEnvio;
    }

    public String getEstado() { return estado; }

    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty())
            throw new IllegalArgumentException("Estado no puede estar vacío");
        this.estado = estado;
    }
}