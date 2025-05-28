package com.tienda.repositories.facturas;

import com.tienda.models.facturas.Cliente;
import java.util.*;

public class ClienteRepositorio {
    private final Map<Integer, Cliente> clientes = new HashMap<>();

    public void agregar(Cliente cliente) {
        validar(cliente);
        if (clientes.containsKey(cliente.getId())) {
            throw new IllegalArgumentException("Ya existe un cliente con ese ID");
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
        clientes.put(id, cliente);
        return cliente;
    }

    public void eliminar(int id) {
        validarId(id);
        clientes.remove(id);
    }

    private void validar(Cliente c) {
        if (c == null || c.getNombre() == null || c.getNombre().isEmpty()) {
            throw new IllegalArgumentException("Datos inválidos del cliente");
        }
    }

    private void validarId(int id) {
        if (!clientes.containsKey(id)) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
    }
}
