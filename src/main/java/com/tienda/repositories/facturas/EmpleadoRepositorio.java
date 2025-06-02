package com.tienda.repositories.facturas;

import com.tienda.models.facturas.Empleado;
import java.util.*;

public class EmpleadoRepositorio {
    private final Map<Integer, Empleado> empleados = new HashMap<>();

    public void agregar(Empleado empleado) {
        validarEmpleadoNoNulo(empleado);
        validarEmpleadoNoExiste(empleado.getId());
        empleados.put(empleado.getId(), empleado);
    }

    public List<Empleado> obtenerTodos() {
        return new ArrayList<>(empleados.values());
    }

    public Empleado obtener(int id) {
        validarEmpleadoExiste(id);
        return empleados.get(id);
    }

    public Empleado actualizar(int id, Empleado empleadoActualizado) {
        validarEmpleadoExiste(id);
        validarEmpleadoNoNulo(empleadoActualizado);
        empleados.put(id, empleadoActualizado);
        return empleadoActualizado;
    }

    public boolean eliminar(int id) {
        validarEmpleadoExiste(id);
        empleados.remove(id);
        return true;
    }

    private void validarEmpleadoNoNulo(Empleado empleado) {
        if (empleado == null || empleado.getNombre() == null || empleado.getNombre().isBlank()) {
            throw new IllegalArgumentException("Datos inválidos del empleado: el nombre no puede estar vacío.");
        }
    }

    private void validarEmpleadoExiste(int id) {
        if (!empleados.containsKey(id)) {
            throw new IllegalArgumentException("Empleado no encontrado con ID: " + id);
        }
    }

    private void validarEmpleadoNoExiste(int id) {
        if (empleados.containsKey(id)) {
            throw new IllegalArgumentException("Ya existe un empleado con ID: " + id);
        }
    }
}
