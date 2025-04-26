package com.tienda.repositories;

import com.tienda.models.Categoria;
import java.util.HashMap;
import java.util.Map;

public class CategoriaRepositorio {
    private Map<Integer, Categoria> categorias = new HashMap<>();

    public void agregar(Categoria categoria) {
        categorias.put(categoria.getId(), categoria);
    }

    public Map<Integer, Categoria> obtenerTodos() {
        return categorias;
    }

    public Categoria obtener(int id) {
        return categorias.get(id);
    }

    public Categoria actualizar(int id, Categoria nuevaCategoria) {
        if (categorias.containsKey(id)) {
            categorias.put(id, nuevaCategoria);
            return nuevaCategoria;
        }
        return null;
    }

    public boolean eliminar(int id) {
        return categorias.remove(id) != null;
    }
}