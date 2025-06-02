package com.tienda.repositories.facturas;

import com.tienda.models.facturas.Categoria;
import java.util.*;

public class CategoriaRepositorio {
    private final Map<Integer, Categoria> categorias = new HashMap<>();

    public void agregar(Categoria categoria) {
        validarCategoria(categoria);
        if (categorias.containsKey(categoria.getId())) {
            throw new IllegalArgumentException("Ya existe una categoría con el ID: " + categoria.getId());
        }
        categorias.put(categoria.getId(), categoria);
    }

    public List<Categoria> obtenerTodos() {
        return new ArrayList<>(categorias.values());
    }

    public Categoria obtener(int id) {
        validarId(id);
        return categorias.get(id);
    }

    public Categoria actualizar(int id, Categoria categoria) {
        validarId(id);
        validarCategoria(categoria);
        if (id != categoria.getId()) {
            throw new IllegalArgumentException("El ID en la ruta y el ID del cuerpo no coinciden.");
        }
        categorias.put(id, categoria);
        return categoria;
    }

    public void eliminar(int id) {
        validarId(id);
        categorias.remove(id);
    }

    private void validarId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        if (!categorias.containsKey(id)) {
            throw new IllegalArgumentException("Categoría con ID " + id + " no encontrada.");
        }
    }

    private void validarCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("La categoría no puede ser nula.");
        }
        if (categoria.getId() <= 0) {
            throw new IllegalArgumentException("El ID de la categoría debe ser mayor que cero.");
        }
        if (categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío.");
        }
    }
}
