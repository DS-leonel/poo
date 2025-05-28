package com.tienda.models.inventarios;

public class Transferencia {
    private int id;
    private int idProducto;
    private int cantidad;
    private int idAlmacenOrigen;
    private int idAlmacenDestino;
    private String fecha;
    private String estado;

    public Transferencia() {}

    public Transferencia(int id, int idProducto, int cantidad, int idAlmacenOrigen, int idAlmacenDestino, String fecha, String estado) {
        setId(id);
        setIdProducto(idProducto);
        setCantidad(cantidad);

        this.idAlmacenDestino = 0;
        setIdAlmacenOrigen(idAlmacenOrigen);
        this.idAlmacenOrigen = idAlmacenOrigen;
        setIdAlmacenDestino(idAlmacenDestino);
        setFecha(fecha);
        setEstado(estado);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la transferencia debe ser > 0");
        }
        this.id = id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        if (idProducto <= 0) {
            throw new IllegalArgumentException("El ID del producto en la transferencia debe ser > 0");
        }
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a transferir debe ser > 0");
        }
        this.cantidad = cantidad;
    }

    public int getIdAlmacenOrigen() {
        return idAlmacenOrigen;
    }

    public void setIdAlmacenOrigen(int idAlmacenOrigen) {
        if (idAlmacenOrigen <= 0) {
            throw new IllegalArgumentException("El ID del almacén de origen debe ser > 0");
        }
        if (this.idAlmacenDestino != 0 && idAlmacenOrigen == this.idAlmacenDestino) {
            throw new IllegalArgumentException("El almacén de origen no puede ser igual al de destino.");
        }
        this.idAlmacenOrigen = idAlmacenOrigen;
    }

    public int getIdAlmacenDestino() {
        return idAlmacenDestino;
    }

    public void setIdAlmacenDestino(int idAlmacenDestino) {
        if (idAlmacenDestino <= 0) {
            throw new IllegalArgumentException("El ID del almacén de destino debe ser > 0");
        }
        if (this.idAlmacenOrigen != 0 && idAlmacenDestino == this.idAlmacenOrigen) {
            throw new IllegalArgumentException("El almacén de destino no puede ser igual al de origen.");
        }
        this.idAlmacenDestino = idAlmacenDestino;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha de la transferencia no puede estar vacía.");
        }

        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado de la transferencia no puede estar vacío.");
        }

        this.estado = estado.trim().toUpperCase();
    }

}