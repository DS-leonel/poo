package com.tienda.repositories.inventarios;

import com.tienda.models.inventarios.Producto;
import java.util.*;

public class ProductoRepositorio {
    private final Map<Integer, Producto> productos = new HashMap<>();

    public void agregar(Producto p) {
        validar(p);
        if (productos.containsKey(p.getId())) {
            throw new IllegalArgumentException("Ya existe un producto con ese ID");
        }
        productos.put(p.getId(), p);
    }

    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos.values());
    }

    public Producto obtener(int id) {
        validarId(id);
        return productos.get(id);
    }

    public Producto actualizar(int id, Producto p) {
        validarId(id);
        validar(p);
        productos.put(id, p);
        return p;
    }

    public void eliminar(int id) {
        validarId(id);
        productos.remove(id);
    }

    private void validar(Producto p) {
        if (p == null || p.getNombre() == null || p.getNombre().isEmpty() || p.getPrecio() < 0) {
            throw new IllegalArgumentException("Datos inválidos del producto");
        }
    }

    private void validarId(int id) {
        if (!productos.containsKey(id)) {
            throw new IllegalArgumentException("Producto no encontrado");
        }
    }
}
