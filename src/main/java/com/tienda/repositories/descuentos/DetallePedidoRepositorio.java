package com.tienda.repositories.descuentos;

import com.tienda.models.descuentos.DetallePedido;
import java.util.*;

public class DetallePedidoRepositorio {
    private final Map<Integer, DetallePedido> detalles = new HashMap<>();

    public void agregar(DetallePedido detalle) {
        validarDetalle(detalle);
        if (detalles.containsKey(detalle.getId())) {
            throw new IllegalArgumentException("Ya existe un detalle con ID: " + detalle.getId());
        }
        detalles.put(detalle.getId(), detalle);
    }

    public List<DetallePedido> obtenerTodos() {
        return new ArrayList<>(detalles.values());
    }

    public DetallePedido obtenerPorId(int id) {
        validarId(id);
        return detalles.get(id);
    }

    public DetallePedido actualizar(int id, DetallePedido detalle) {
        validarId(id);
        validarDetalle(detalle);
        if (id != detalle.getId()) {
            throw new IllegalArgumentException("El ID del detalle en la ruta y en el cuerpo deben coincidir.");
        }
        detalles.put(id, detalle);
        return detalle;
    }

    public void eliminar(int id) {
        validarId(id);
        detalles.remove(id);
    }

    private void validarDetalle(DetallePedido detalle) {
        if (detalle == null) {
            throw new IllegalArgumentException("El detalle no puede ser nulo.");
        }
        if (detalle.getId() <= 0) {
            throw new IllegalArgumentException("El ID del detalle debe ser mayor que cero.");
        }
        if (detalle.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
    }

    private void validarId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        if (!detalles.containsKey(id)) {
            throw new IllegalArgumentException("Detalle con ID " + id + " no encontrado.");
        }
    }
}
