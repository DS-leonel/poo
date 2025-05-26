package com.tienda.repositories;

import com.tienda.models.Empleado;
import java.util.*;

public class EmpleadoRepositorio {
    private final Map<Integer, Empleado> empleados = new HashMap<>();

    public void agregar(Empleado e) {
        validar(e);
        if (empleados.containsKey(e.getId())) {
            throw new IllegalArgumentException("Ya existe un empleado con ese ID");
        }
        empleados.put(e.getId(), e);
    }

    public List<Empleado> obtenerTodos() {
        return new ArrayList<>(empleados.values());
    }

    public Empleado obtener(int id) {
        validarId(id);
        return empleados.get(id);
    }

    public Empleado actualizar(int id, Empleado e) {
        validarId(id);
        validar(e);
        empleados.put(id, e);
        return e;
    }

    public void eliminar(int id) {
        validarId(id);
        empleados.remove(id);
    }

    private void validar(Empleado e) {
        if (e == null || e.getNombre() == null || e.getNombre().isEmpty()) {
            throw new IllegalArgumentException("Datos inválidos del empleado");
        }
    }

    private void validarId(int id) {
        if (!empleados.containsKey(id)) {
            throw new IllegalArgumentException("Empleado no encontrado");
        }
    }
}
