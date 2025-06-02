package com.tienda.repositories.inventarios;

import com.tienda.models.inventarios.Proveedor;
import java.util.*;

public class ProveedorRepositorio {
    private final Map<Integer, Proveedor> proveedores = new HashMap<>();

    public void agregar(Proveedor proveedor) {
        validarProveedor(proveedor);
        if (proveedores.containsKey(proveedor.getId())) {
            throw new IllegalArgumentException("Ya existe un proveedor con el ID: " + proveedor.getId());
        }
        proveedores.put(proveedor.getId(), proveedor);
    }

    public List<Proveedor> obtenerTodos() {
        return new ArrayList<>(proveedores.values());
    }

    public Proveedor obtener(int id) {
        validarIdExistente(id);
        return proveedores.get(id);
    }

    public Proveedor actualizar(int id, Proveedor proveedor) {
        validarIdExistente(id);
        validarProveedor(proveedor);
        proveedor.setId(id);
        proveedores.put(id, proveedor);
        return proveedor;
    }

    public void eliminar(int id) {
        validarIdExistente(id);
        proveedores.remove(id);
    }

    private void validarIdExistente(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del proveedor debe ser mayor que cero.");
        }
        if (!proveedores.containsKey(id)) {
            throw new IllegalArgumentException("Proveedor con ID " + id + " no encontrado.");
        }
    }

    private void validarProveedor(Proveedor proveedor) {
        if (proveedor == null) {
            throw new IllegalArgumentException("Los datos del proveedor no pueden ser nulos.");
        }
        if (proveedor.getNombre() == null || proveedor.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proveedor no puede estar vacío.");
        }
        if (proveedor.getTelefono() == null || proveedor.getTelefono().trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono del proveedor no puede estar vacío.");
        }
    }
}
