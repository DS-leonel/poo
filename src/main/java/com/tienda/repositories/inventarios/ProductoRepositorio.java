package com.tienda.repositories.inventarios;

import com.tienda.models.inventarios.Producto;
import java.util.*;

public class ProductoRepositorio {
    private final Map<Integer, Producto> productos = new HashMap<>();

    public void agregar(Producto producto) {
        validarProducto(producto);
        if (productos.containsKey(producto.getId())) {
            throw new IllegalArgumentException("Ya existe un producto con ese ID: " + producto.getId());
        }
        productos.put(producto.getId(), producto);
    }

    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos.values());
    }

    public Producto obtener(int id) {
        validarIdExistente(id);
        return productos.get(id);
    }

    public Producto actualizar(int id, Producto producto) {
        validarIdExistente(id);
        validarProducto(producto);
        productos.put(id, producto);
        return producto;
    }

    public void eliminar(int id) {
        validarIdExistente(id);
        productos.remove(id);
    }

    private void validarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo.");
        }
    }

    private void validarIdExistente(int id) {
        if (!productos.containsKey(id)) {
            throw new IllegalArgumentException("Producto con ID " + id + " no encontrado.");
        }
    }
}
