package com.tienda.repositories.inventarios;

import com.tienda.models.inventarios.Sucursal;
import java.util.*;

public class SucursalRepositorio {
    private final Map<Integer, Sucursal> sucursales = new HashMap<>();

    public void agregar(Sucursal sucursal) {
        validarSucursal(sucursal);
        if (sucursales.containsKey(sucursal.getId())) {
            throw new IllegalArgumentException("Ya existe una sucursal con el ID: " + sucursal.getId());
        }
        sucursales.put(sucursal.getId(), sucursal);
    }

    public List<Sucursal> obtenerTodos() {
        return new ArrayList<>(sucursales.values());
    }

    public Sucursal obtener(int id) {
        validarIdExistente(id);
        return sucursales.get(id);
    }

    public Sucursal actualizar(int id, Sucursal sucursal) {
        validarIdExistente(id);
        validarSucursal(sucursal);
        sucursal.setId(id);
        sucursales.put(id, sucursal);
        return sucursal;
    }

    public void eliminar(int id) {
        validarIdExistente(id);
        sucursales.remove(id);
    }

    private void validarIdExistente(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la sucursal debe ser mayor que cero.");
        }
        if (!sucursales.containsKey(id)) {
            throw new IllegalArgumentException("Sucursal con ID " + id + " no encontrada.");
        }
    }

    private void validarSucursal(Sucursal sucursal) {
        if (sucursal == null) {
            throw new IllegalArgumentException("Los datos de la sucursal no pueden ser nulos.");
        }
        if (sucursal.getNombre() == null || sucursal.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la sucursal no puede estar vacío.");
        }
    }
}
