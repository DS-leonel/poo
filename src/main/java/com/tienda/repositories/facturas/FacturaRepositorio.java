package com.tienda.repositories.facturas;

import com.tienda.models.facturas.Factura;
import java.util.*;

public class FacturaRepositorio {
    private final Map<Integer, Factura> facturas = new HashMap<>();

    public void agregar(Factura factura) {
        validarFacturaNoNula(factura);
        validarFacturaNoExiste(factura.getId());
        facturas.put(factura.getId(), factura);
    }

    public List<Factura> obtenerTodos() {
        return new ArrayList<>(facturas.values());
    }

    public Factura obtener(int id) {
        validarFacturaExiste(id);
        return facturas.get(id);
    }

    public Factura actualizar(int id, Factura facturaActualizada) {
        validarFacturaExiste(id);
        validarFacturaNoNula(facturaActualizada);
        facturas.put(id, facturaActualizada);
        return facturaActualizada;
    }

    public boolean eliminar(int id) {
        validarFacturaExiste(id);
        facturas.remove(id);
        return true;
    }

    private void validarFacturaNoNula(Factura factura) {
        if (factura == null || factura.getTotal() < 0) {
            throw new IllegalArgumentException("Datos inválidos de factura: factura nula o total negativo.");
        }
    }

    private void validarFacturaExiste(int id) {
        if (!facturas.containsKey(id)) {
            throw new IllegalArgumentException("Factura no encontrada con ID: " + id);
        }
    }

    private void validarFacturaNoExiste(int id) {
        if (facturas.containsKey(id)) {
            throw new IllegalArgumentException("Ya existe una factura con ID: " + id);
        }
    }
}
