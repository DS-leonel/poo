package com.tienda.repositories.seguridad;

import com.tienda.models.seguridad.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {
    private List<Usuario> usuarios = new ArrayList<>();

    public List<Usuario> obtenerTodos() {
        return usuarios;
    }

    public void guardar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void eliminarPorId(int id) {
        usuarios.removeIf(u -> u.getId() == id);
    }

    public Usuario buscarPorId(int id) {
        return usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }
}