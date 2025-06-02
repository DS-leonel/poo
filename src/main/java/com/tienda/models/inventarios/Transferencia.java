package com.tienda.models.inventarios;

import java.time.LocalDate;

public class Transferencia {
    private int id;
    private int idProducto;
    private int cantidad;
    private int idAlmacenOrigen;
    private int idAlmacenDestino;
    private LocalDate fecha; // LocalDate para la fecha
    private String estado;

    public Transferencia() {}

    public Transferencia(int id, int idProducto, int cantidad, int idAlmacenOrigen, int idAlmacenDestino, LocalDate fecha, String estado) {
        setId(id);
        setIdProducto(idProducto);
        setCantidad(cantidad);
        setIdAlmacenOrigen(idAlmacenOrigen);
        setIdAlmacenDestino(idAlmacenDestino);
        setFecha(fecha);
        setEstado(estado);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("El ID de la transferencia debe ser > 0");
        this.id = id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        if (idProducto <= 0) throw new IllegalArgumentException("El ID del producto debe ser > 0");
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser > 0");
        this.cantidad = cantidad;
    }

    public int getIdAlmacenOrigen() {
        return idAlmacenOrigen;
    }

    public void setIdAlmacenOrigen(int idAlmacenOrigen) {
        if (idAlmacenOrigen <= 0) throw new IllegalArgumentException("ID de almacén origen debe ser > 0");
        if (this.idAlmacenDestino != 0 && idAlmacenOrigen == this.idAlmacenDestino) {
            throw new IllegalArgumentException("Almacén origen no puede ser igual al destino.");
        }
        this.idAlmacenOrigen = idAlmacenOrigen;
    }

    public int getIdAlmacenDestino() {
        return idAlmacenDestino;
    }

    public void setIdAlmacenDestino(int idAlmacenDestino) {
        if (idAlmacenDestino <= 0) throw new IllegalArgumentException("ID de almacén destino debe ser > 0");
        if (this.idAlmacenOrigen != 0 && idAlmacenDestino == this.idAlmacenOrigen) {
            throw new IllegalArgumentException("Almacén destino no puede ser igual al origen.");
        }
        this.idAlmacenDestino = idAlmacenDestino;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        if (fecha == null) throw new IllegalArgumentException("La fecha no puede ser nula");
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) throw new IllegalArgumentException("Estado no puede estar vacío");
        this.estado = estado.trim().toUpperCase();
    }
}
