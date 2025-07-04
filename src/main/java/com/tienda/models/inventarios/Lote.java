package com.tienda.models.inventarios;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Lote {
    private int id;
    private String numeroLote;
    private int productoId;
    private LocalDate fechaCaducidad;
    private int cantidad;

    public Lote() {
    }

    public Lote(int id, String numeroLote, int productoId, String fechaCaducidadStr, int cantidad) {
        setId(id);
        setNumeroLote(numeroLote);
        setProductoId(productoId);
        setFechaCaducidad(fechaCaducidadStr);
        setCantidad(cantidad);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID del lote debe ser > 0");
        }
        this.id = id;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        if (numeroLote == null || numeroLote.trim().isEmpty()) {
            throw new IllegalArgumentException("Número de lote no puede estar vacío");
        }
        this.numeroLote = numeroLote.trim();
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        if (productoId <= 0) {
            throw new IllegalArgumentException("ID del producto asociado al lote debe ser > 0");
        }
        this.productoId = productoId;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidadStr) {
        if (fechaCaducidadStr == null || fechaCaducidadStr.trim().isEmpty()) {
            this.fechaCaducidad = null;
            return;
        }
        try {
            this.fechaCaducidad = LocalDate.parse(fechaCaducidadStr.trim());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha de caducidad inválido. Usar YYYY-MM-DD.", e);
        }
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad del lote no puede ser negativa.");
        }
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Lote{" +
                "id=" + id +
                ", numeroLote='" + numeroLote + '\'' +
                ", productoId=" + productoId +
                ", fechaCaducidad=" + fechaCaducidad +
                ", cantidad=" + cantidad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lote)) return false;
        Lote lote = (Lote) o;
        return id == lote.id &&
                productoId == lote.productoId &&
                cantidad == lote.cantidad &&
                Objects.equals(numeroLote, lote.numeroLote) &&
                Objects.equals(fechaCaducidad, lote.fechaCaducidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroLote, productoId, fechaCaducidad, cantidad);
    }
}
