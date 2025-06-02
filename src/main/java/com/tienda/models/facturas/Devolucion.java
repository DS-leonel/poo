package com.tienda.models.facturas;


public class Devolucion {
    private int id;
    private int idFactura;
    private int idProducto;
    private int cantidad;
    private String motivo;
    private String fecha;
    private String estado;

    public Devolucion() {}

    public Devolucion(int id, int idFactura, int idProducto, int cantidad, String motivo, String fecha, String estado) {
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
        if (id <= 0) throw new IllegalArgumentException("El ID de la devolución debe ser > 0");
        this.id = id;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        if (idFactura <= 0) throw new IllegalArgumentException("El ID de la factura debe ser > 0");
        this.idFactura = idFactura;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        if (motivo == null || motivo.trim().isEmpty())
            throw new IllegalArgumentException("El motivo no puede estar vacío");
        this.motivo = motivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty())
            throw new IllegalArgumentException("La fecha no puede estar vacía");
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty())
            throw new IllegalArgumentException("El estado no puede estar vacío");
        this.estado = estado;
    }


}