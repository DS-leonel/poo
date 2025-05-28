package com.tienda.repositories.descuentos;

import com.tienda.models.descuentos.DetallePedido;
import java.util.*;

public class DetallePedidoRepositorio {
    private final Map<Integer, DetallePedido> detalles = new HashMap<>();

    public void agregar(DetallePedido d) {
        validar(d);
        if (detalles.containsKey(d.getId())) {
            throw new IllegalArgumentException("Ya existe un detalle con ese ID");
        }
        detalles.put(d.getId(), d);
    }

    public List<DetallePedido> obtenerTodos() {
        return new ArrayList<>(detalles.values());
    }

    public DetallePedido obtener(int id) {
        validarId(id);
        return detalles.get(id);
    }

    public DetallePedido actualizar(int id, DetallePedido d) {
        validarId(id);
        validar(d);
        detalles.put(id, d);
        return d;
    }

    public void eliminar(int id) {
        validarId(id);
        detalles.remove(id);
    }

    private void validar(DetallePedido d) {
        if (d == null || d.getCantidad() <= 0) {
            throw new IllegalArgumentException("Datos inválidos del detalle");
        }
    }

    private void validarId(int id) {
        if (!detalles.containsKey(id)) {
            throw new IllegalArgumentException("Detalle no encontrado");
        }
    }
}
