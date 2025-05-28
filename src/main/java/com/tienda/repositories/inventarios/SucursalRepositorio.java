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
        validarId(id);
        return sucursales.get(id);
    }

    public Sucursal actualizar(int id, Sucursal sucursal) {
        validarId(id);
        validarSucursal(sucursal);
        sucursales.put(id, sucursal);
        return sucursal;
    }

    public void eliminar(int id) {
        validarId(id);
        sucursales.remove(id);
    }

    private void validarId(int id) {
        if (!sucursales.containsKey(id)) {
            throw new IllegalArgumentException("Sucursal no encontrada");
        }
    }

    private void validarSucursal(Sucursal sucursal) {
        if (sucursal == null || sucursal.getNombre() == null || sucursal.getNombre().isEmpty()) {
            throw new IllegalArgumentException("Datos inválidos de sucursal");
        }
    }
}

