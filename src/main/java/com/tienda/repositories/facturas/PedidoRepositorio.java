package com.tienda.repositories.facturas;

import com.tienda.models.facturas.Pedido;
import java.util.*;

public class PedidoRepositorio {
    private final Map<Integer, Pedido> pedidos = new HashMap<>();

    public void agregar(Pedido p) {
        validar(p);
        if (pedidos.containsKey(p.getId())) {
            throw new IllegalArgumentException("Ya existe un pedido con ese ID");
        }
        pedidos.put(p.getId(), p);
    }

    public List<Pedido> obtenerTodos() {
        return new ArrayList<>(pedidos.values());
    }

    public Pedido obtener(int id) {
        validarId(id);
        return pedidos.get(id);
    }

    public Pedido actualizar(int id, Pedido p) {
        validarId(id);
        validar(p);
        pedidos.put(id, p);
        return p;
    }

    public void eliminar(int id) {
        validarId(id);
        pedidos.remove(id);
    }

    private void validar(Pedido p) {
        if (p == null) {
            throw new IllegalArgumentException("Datos inválidos del pedido");
        }
        if (p.getFecha() == null) {
            throw new IllegalArgumentException("La fecha del pedido es inválida");
        }
    }

    private void validarId(int id) {
        if (!pedidos.containsKey(id)) {
            throw new IllegalArgumentException("Pedido no encontrado");
        }
    }
}
