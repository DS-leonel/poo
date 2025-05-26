package com.tienda.repositories;

import com.tienda.models.Categoria;
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
        categorias.put(id, categoria);
        return categoria;
    }

    public void eliminar(int id) {
        validarId(id);
        categorias.remove(id);
    }

    private void validarId(int id) {
        if (!categorias.containsKey(id)) {
            throw new IllegalArgumentException("Categoría no encontrada");
        }
    }

    private void validarCategoria(Categoria categoria) {
        if (categoria == null || categoria.getNombre() == null || categoria.getNombre().isEmpty()) {
            throw new IllegalArgumentException("Datos inválidos de categoría");
        }
    }
}
