package com.tienda.repositories.facturas;

import com.tienda.models.facturas.Factura;
import java.util.*;

public class FacturaRepositorio {
    private final Map<Integer, Factura> facturas = new HashMap<>();

    public void agregar(Factura factura) {
        validarFactura(factura);
        if (facturas.containsKey(factura.getId())) {
            throw new IllegalArgumentException("Ya existe una factura con el ID: " + factura.getId());
        }
        facturas.put(factura.getId(), factura);
    }

    public List<Factura> obtenerTodos() {
        return new ArrayList<>(facturas.values());
    }

    public Factura obtener(int id) {
        validarId(id);
        return facturas.get(id);
    }

    public Factura actualizar(int id, Factura factura) {
        validarId(id);
        validarFactura(factura);
        facturas.put(id, factura);
        return factura;
    }

    public void eliminar(int id) {
        validarId(id);
        facturas.remove(id);
    }

    private void validarId(int id) {
        if (!facturas.containsKey(id)) {
            throw new IllegalArgumentException("Factura no encontrada");
        }
    }

    private void validarFactura(Factura factura) {
        if (factura == null || factura.getTotal() < 0) {
            throw new IllegalArgumentException("Datos inválidos de factura");
        }
    }
}

