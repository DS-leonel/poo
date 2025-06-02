package com.tienda.repositories.descuentos;

import com.tienda.models.descuentos.Descuento;
import java.util.*;

public class DescuentoRepositorio {

    private final Map<Integer, Descuento> descuentos = new HashMap<>();

    public void agregar(Descuento descuento) {
        validarNuevoDescuento(descuento);
        descuentos.put(descuento.getId(), descuento);
    }

    public List<Descuento> obtenerTodos() {
        return new ArrayList<>(descuentos.values());
    }

    public Descuento obtener(int id) {
        validarExistencia(id);
        return descuentos.get(id);
    }

    public Descuento actualizar(int id, Descuento descuentoActualizado) {
        validarExistencia(id);
        validarDescuento(descuentoActualizado);
        if (descuentoActualizado.getId() != id) {
            throw new IllegalArgumentException("El ID del descuento no coincide con el ID proporcionado.");
        }
        descuentos.put(id, descuentoActualizado);
        return descuentoActualizado;
    }

    public void eliminar(int id) {
        validarExistencia(id);
        descuentos.remove(id);
    }

    private void validarExistencia(int id) {
        if (!descuentos.containsKey(id)) {
            throw new NoSuchElementException("No existe un descuento con ID: " + id);
        }
    }

    private void validarNuevoDescuento(Descuento descuento) {
        validarDescuento(descuento);
        if (descuentos.containsKey(descuento.getId())) {
            throw new IllegalArgumentException("Ya existe un descuento con el ID: " + descuento.getId());
        }
    }

    private void validarDescuento(Descuento descuento) {
        if (descuento == null) {
            throw new IllegalArgumentException("El descuento no puede ser nulo.");
        }
        double porcentaje = descuento.getPorcentaje();
        if (porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 100.");
        }
    }
}
