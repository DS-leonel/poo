package com.tienda.models.facturas;

import java.time.LocalDate;
import java.util.Objects;

public class Devolucion {

    private int id;
    private int idFactura;
    private int idProducto;
    private int cantidad;
    private String motivo;
    private LocalDate fecha;
    private String estado;

    public Devolucion() {}

    public Devolucion(int id, int idFactura, int idProducto, int cantidad, String motivo, LocalDate fecha, String estado) {
        setId(id);
        setIdFactura(idFactura);
        setIdProducto(idProducto);
        setCantidad(cantidad);
        setMotivo(motivo);
        setFecha(fecha);
        setEstado(estado);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("El ID de la devolución debe ser mayor que cero.");
        this.id = id;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        if (idFactura <= 0) throw new IllegalArgumentException("El ID de la factura debe ser mayor que cero.");
        this.idFactura = idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        if (idProducto <= 0) throw new IllegalArgumentException("El ID del producto debe ser mayor que cero.");
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        if (motivo == null || motivo.trim().isEmpty())
            throw new IllegalArgumentException("El motivo no puede estar vacío.");
        this.motivo = motivo.trim();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        if (fecha == null)
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty())
            throw new IllegalArgumentException("El estado no puede estar vacío.");
        this.estado = estado.trim();
    }

    @Override
    public String toString() {
        return "Devolucion{" +
                "id=" + id +
                ", idFactura=" + idFactura +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                ", motivo='" + motivo + '\'' +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Devolucion)) return false;
        Devolucion that = (Devolucion) o;
        return id == that.id &&
                idFactura == that.idFactura &&
                idProducto == that.idProducto &&
                cantidad == that.cantidad &&
                motivo.equals(that.motivo) &&
                fecha.equals(that.fecha) &&
                estado.equals(that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idFactura, idProducto, cantidad, motivo, fecha, estado);
    }
}
