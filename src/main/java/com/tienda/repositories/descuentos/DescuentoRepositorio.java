package com.tienda.repositories.descuentos;

import com.tienda.models.descuentos.Descuento;
import java.util.*;

public class DescuentoRepositorio {
    private final Map<Integer, Descuento> descuentos = new HashMap<>();

    public void agregar(Descuento descuento) {
        validarDescuento(descuento);
        if (descuentos.containsKey(descuento.getId())) {
            throw new IllegalArgumentException("Ya existe un descuento con el ID: " + descuento.getId());
        }
        descuentos.put(descuento.getId(), descuento);
    }

    public List<Descuento> obtenerTodos() {
        return new ArrayList<>(descuentos.values());
    }

    public Descuento obtener(int id) {
        validarId(id);
        return descuentos.get(id);
    }

    public Descuento actualizar(int id, Descuento descuento) {
        validarId(id);
        validarDescuento(descuento);
        descuentos.put(id, descuento);
        return descuento;
    }

    public void eliminar(int id) {
        validarId(id);
        descuentos.remove(id);
    }

    private void validarId(int id) {
        if (!descuentos.containsKey(id)) {
            throw new IllegalArgumentException("Descuento no encontrado");
        }
    }

    private void validarDescuento(Descuento descuento) {
        if (descuento == null || descuento.getPorcentaje() < 0 || descuento.getPorcentaje() > 100) {
            throw new IllegalArgumentException("Datos inválidos de descuento");
        }
    }
}

