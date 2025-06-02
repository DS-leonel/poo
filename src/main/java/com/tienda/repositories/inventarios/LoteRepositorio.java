package com.tienda.repositories.inventarios;

import com.tienda.models.inventarios.Lote;
import java.util.*;
import java.util.stream.Collectors;

public class LoteRepositorio {
    private final Map<Integer, Lote> lotes = new HashMap<>();

    public void agregar(Lote lote) {
        validarLote(lote);
        if (lotes.containsKey(lote.getId())) {
            throw new IllegalArgumentException("Ya existe un lote con ese ID: " + lote.getId());
        }
        lotes.put(lote.getId(), lote);
    }

    public List<Lote> obtenerTodos() {
        return new ArrayList<>(lotes.values());
    }

    public Lote obtener(int id) {
        validarIdExistente(id);
        return lotes.get(id);
    }

    public List<Lote> obtenerPorProductoId(int productoId) {
        if (productoId <= 0) {
            throw new IllegalArgumentException("ID del producto debe ser mayor que 0 para buscar lotes.");
        }
        return lotes.values().stream()
                .filter(lote -> lote.getProductoId() == productoId)
                .collect(Collectors.toList());
    }

    public Lote actualizar(int id, Lote lote) {
        validarIdExistente(id);
        validarLote(lote);
        if (id != lote.getId()) {
            throw new IllegalArgumentException("El ID del lote en el path y en el cuerpo no coinciden.");
        }
        lotes.put(id, lote);
        return lote;
    }

    public void eliminar(int id) {
        validarIdExistente(id);
        lotes.remove(id);
    }

    private void validarLote(Lote lote) {
        if (lote == null) {
            throw new IllegalArgumentException("El lote no puede ser nulo.");
        }
        if (lote.getId() <= 0) {
            throw new IllegalArgumentException("El ID del lote debe ser mayor que 0.");
        }
        if (lote.getNumeroLote() == null || lote.getNumeroLote().trim().isEmpty()) {
            throw new IllegalArgumentException("El número de lote no puede estar vacío.");
        }
        if (lote.getProductoId() <= 0) {
            throw new IllegalArgumentException("El ID del producto asociado al lote debe ser mayor que 0.");
        }
        if (lote.getCantidad() < 0) {
            throw new IllegalArgumentException("La cantidad del lote no puede ser negativa.");
        }
    }

    private void validarIdExistente(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del lote debe ser mayor que 0.");
        }
        if (!lotes.containsKey(id)) {
            throw new IllegalArgumentException("Lote con ID " + id + " no encontrado.");
        }
    }
}
