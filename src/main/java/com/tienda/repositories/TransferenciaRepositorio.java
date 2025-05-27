package com.tienda.repositories;

import com.tienda.models.Transferencia;
import java.util.*;

public class TransferenciaRepositorio {
    private final Map<Integer, Transferencia> transferencias = new HashMap<>();

    public void agregar(Transferencia transferencia) {
        validarTransferenciaCompleta(transferencia);
        if (transferencias.containsKey(transferencia.getId())) {
            throw new IllegalArgumentException("Ya existe una transferencia con el ID: " + transferencia.getId());
        }
        transferencias.put(transferencia.getId(), transferencia);
    }

    public List<Transferencia> obtenerTodos() {
        return new ArrayList<>(transferencias.values());
    }

    public Transferencia obtener(int id) {
        validarIdExistente(id);
        return transferencias.get(id);
    }

    public Transferencia actualizar(int id, Transferencia transferencia) {
        validarIdExistente(id);
        transferencia.setId(id);
        validarTransferenciaCompleta(transferencia);
        transferencias.put(id, transferencia);
        return transferencia;
    }

    public void eliminar(int id) {
        validarIdExistente(id);
        transferencias.remove(id);
    }

    private void validarIdExistente(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la transferencia debe ser > 0");
        }
        if (!transferencias.containsKey(id)) {
            throw new IllegalArgumentException("Transferencia con ID " + id + " no encontrada.");
        }
    }

    private void validarTransferenciaCompleta(Transferencia transferencia) {
        if (transferencia == null) {
            throw new IllegalArgumentException("Los datos de la transferencia no pueden ser nulos.");
        }

        if (transferencia.getId() <= 0) {
            throw new IllegalArgumentException("ID de transferencia inválido en el objeto.");
        }
        if (transferencia.getIdProducto() <= 0) {
            throw new IllegalArgumentException("ID de producto inválido en la transferencia.");
        }
        if (transferencia.getCantidad() <= 0) {
            throw new IllegalArgumentException("Cantidad inválida en la transferencia.");
        }
        if (transferencia.getIdAlmacenOrigen() <= 0) {
            throw new IllegalArgumentException("ID de almacén origen inválido en la transferencia.");
        }
        if (transferencia.getIdAlmacenDestino() <= 0) {
            throw new IllegalArgumentException("ID de almacén destino inválido en la transferencia.");
        }
        if (transferencia.getIdAlmacenOrigen() == transferencia.getIdAlmacenDestino()) {
            throw new IllegalArgumentException("El almacén de origen y destino no pueden ser el mismo.");
        }
        if (transferencia.getFecha() == null || transferencia.getFecha().trim().isEmpty()){
            throw new IllegalArgumentException("Fecha inválida o vacía en la transferencia.");
        }
        if (transferencia.getEstado() == null || transferencia.getEstado().trim().isEmpty()){
            throw new IllegalArgumentException("Estado inválido o vacío en la transferencia.");
        }
    }
}