package com.tienda.repositories.seguridad;

import com.tienda.models.seguridad.Usuario;

import java.util.*;

public class UsuarioRepositorio {
    private final Map<Integer, Usuario> usuarios = new HashMap<>();

    public void agregar(Usuario usuario) {
        validarUsuario(usuario);
        if (usuarios.containsKey(usuario.getId())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese ID: " + usuario.getId());
        }
        usuarios.put(usuario.getId(), usuario);
    }

    public List<Usuario> obtenerTodos() {
        return new ArrayList<>(usuarios.values());
    }

    public Usuario obtener(int id) {
        validarIdExistente(id);
        return usuarios.get(id);
    }

    public Usuario actualizar(int id, Usuario usuario) {
        validarIdExistente(id);
        validarUsuario(usuario);
        if (id != usuario.getId()) {
            throw new IllegalArgumentException("El ID del usuario en el path y en el cuerpo no coinciden.");
        }
        usuarios.put(id, usuario);
        return usuario;
    }

    public void eliminar(int id) {
        validarIdExistente(id);
        usuarios.remove(id);
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El objeto Usuario no puede ser nulo.");
        }
        if (usuario.getId() <= 0) {
            throw new IllegalArgumentException("ID del usuario debe ser mayor que cero.");
        }
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del usuario no puede estar vacío.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty() || !usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("El email del usuario es inválido.");
        }
    }

    private void validarIdExistente(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID del usuario debe ser mayor que cero para buscar, actualizar o eliminar.");
        }
        if (!usuarios.containsKey(id)) {
            throw new IllegalArgumentException("Usuario con ID " + id + " no encontrado.");
        }
    }
}
