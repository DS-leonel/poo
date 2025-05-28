package com.tienda.repositories.inventarios;

import com.tienda.models.inventarios.Inventario;
import java.util.*;

public class InventarioRepositorio {
    private final Map<Integer, Inventario> inventarios = new HashMap<>();

    public void agregar(Inventario inventario) {
        validarInventario(inventario);
        if (inventarios.containsKey(inventario.getId())) {
            throw new IllegalArgumentException("Ya existe un inventario con el ID: " + inventario.getId());
        }
        inventarios.put(inventario.getId(), inventario);
    }

    public List<Inventario> obtenerTodos() {
        return new ArrayList<>(inventarios.values());
    }

    public Inventario obtener(int id) {
        validarId(id);
        return inventarios.get(id);
    }

    public Inventario actualizar(int id, Inventario inventario) {
        validarId(id);
        validarInventario(inventario);
        inventarios.put(id, inventario);
        return inventario;
    }

    public void eliminar(int id) {
        validarId(id);
        inventarios.remove(id);
    }

    private void validarId(int id) {
        if (!inventarios.containsKey(id)) {
            throw new IllegalArgumentException("Inventario no encontrado");
        }
    }

    private void validarInventario(Inventario inventario) {
        if (inventario == null || inventario.getCantidad() < 0) {
            throw new IllegalArgumentException("Datos inválidos de inventario");
        }
    }
}

