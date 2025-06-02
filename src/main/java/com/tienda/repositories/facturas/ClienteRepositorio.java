package com.tienda.repositories.facturas;

import com.tienda.models.facturas.Cliente;
import java.util.*;

public class ClienteRepositorio {
    private final Map<Integer, Cliente> clientes = new HashMap<>();

    public void agregar(Cliente cliente) {
        validar(cliente);
        if (clientes.containsKey(cliente.getId())) {
            throw new IllegalArgumentException("Ya existe un cliente con el ID: " + cliente.getId());
        }
        clientes.put(cliente.getId(), cliente);
    }

    public List<Cliente> obtenerTodos() {
        return new ArrayList<>(clientes.values());
    }

    public Cliente obtener(int id) {
        validarId(id);
        return clientes.get(id);
    }

    public Cliente actualizar(int id, Cliente cliente) {
        validarId(id);
        validar(cliente);
        if (id != cliente.getId()) {
            throw new IllegalArgumentException("El ID en la ruta y el ID del cliente no coinciden.");
        }
        clientes.put(id, cliente);
        return cliente;
    }

    public void eliminar(int id) {
        validarId(id);
        clientes.remove(id);
    }

    private void validar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (cliente.getId() <= 0) {
            throw new IllegalArgumentException("El ID del cliente debe ser mayor que cero.");
        }
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
        }
    }

    private void validarId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        if (!clientes.containsKey(id)) {
            throw new IllegalArgumentException("Cliente con ID " + id + " no encontrado.");
        }
    }
}
