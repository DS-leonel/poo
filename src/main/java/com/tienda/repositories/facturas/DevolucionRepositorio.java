package com.tienda.repositories.facturas;

import com.tienda.models.facturas.Devolucion;
import java.util.*;

public class DevolucionRepositorio {
    private final Map<Integer, Devolucion> devoluciones = new HashMap<>();

    public void agregar(Devolucion devolucion) {
        validar(devolucion);

        if (devoluciones.containsKey(devolucion.getId())) {
            throw new IllegalArgumentException("Ya existe una devolución con ese ID: " + devolucion.getId());
        }
        devoluciones.put(devolucion.getId(), devolucion);
    }

    public List<Devolucion> obtenerTodos() {
        return new ArrayList<>(devoluciones.values());
    }

    public Devolucion obtener(int id) {
        validarId(id);
        Devolucion d = devoluciones.get(id);
        if (d == null) {
            throw new IllegalArgumentException("Devolución no encontrada con ID: " + id);
        }
        return d;
    }

    public Devolucion actualizar(int id, Devolucion devolucion) {
        validarId(id);
        if (!devoluciones.containsKey(id)) {
            throw new IllegalArgumentException("Devolución no encontrada para actualizar con ID: " + id);
        }
        validar(devolucion);
        devolucion.setId(id);
        devoluciones.put(id, devolucion);
        return devolucion;
    }

    public void eliminar(int id) {
        validarId(id);
        if (!devoluciones.containsKey(id)) {
            throw new IllegalArgumentException("Devolución no encontrada para eliminar con ID: " + id);
        }
        devoluciones.remove(id);
    }

    private void validar(Devolucion d) {
        if (d == null) {
            throw new IllegalArgumentException("Los datos de la devolución no pueden ser nulos.");
        }
        if (d.getIdFactura() <= 0) {
            throw new IllegalArgumentException("ID de factura inválido.");
        }
        if (d.getIdProducto() <= 0) {
            throw new IllegalArgumentException("ID de producto inválido.");
        }
        if (d.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva.");
        }
        if (d.getMotivo() == null || d.getMotivo().trim().isEmpty()) {
            throw new IllegalArgumentException("El motivo no puede estar vacío.");
        }
        if (d.getFecha() == null || d.getFecha().trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía.");
        }
        if (d.getEstado() == null || d.getEstado().trim().isEmpty()) {
            throw new IllegalArgumentException("El estado no puede estar vacío.");
        }
    }

    private void validarId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la devolución debe ser un número positivo.");
        }
    }
}
