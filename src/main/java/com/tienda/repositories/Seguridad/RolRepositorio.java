package com.tienda.repositories.Seguridad;

import com.tienda.models.Seguridad.Rol;
import java.util.*;

public class RolRepositorio {
    private final Map<Integer, Rol> roles = new HashMap<>();


    public void agregar(Rol rol) {
        validarRol(rol);

        if (roles.containsKey(rol.getId())) {
            throw new IllegalArgumentException("Ya existe un rol con ese ID: " + rol.getId());
        }
        roles.put(rol.getId(), rol);
    }

    public List<Rol> obtenerTodos() {
        return new ArrayList<>(roles.values());
    }

    public Rol obtener(int id) {
        validarIdExistente(id);
        return roles.get(id);
    }

    public Rol actualizar(int id, Rol rol) {
        validarIdExistente(id);
        validarRol(rol);
        if (id != rol.getId()) {
            throw new IllegalArgumentException("El ID del rol en el path y en el cuerpo no coinciden.");
        }
        roles.put(id, rol);
        return rol;
    }

    public void eliminar(int id) {
        validarIdExistente(id);
        roles.remove(id);
    }

    private void validarRol(Rol rol) {
        if (rol == null) {
            throw new IllegalArgumentException("El objeto Rol no puede ser nulo.");
        }
        if (rol.getId() <= 0) {
            throw new IllegalArgumentException("ID del rol debe ser > 0");
        }
        if (rol.getNombre() == null || rol.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del rol no puede estar vacío.");
        }

    }

    private void validarIdExistente(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID del rol debe ser > 0 para buscar, actualizar o eliminar.");
        }
        if (!roles.containsKey(id)) {
            throw new IllegalArgumentException("Rol con ID " + id + " no encontrado.");
        }
    }
}