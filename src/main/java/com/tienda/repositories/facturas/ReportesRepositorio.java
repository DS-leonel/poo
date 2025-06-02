package com.tienda.repositories.facturas;

import java.util.*;
import com.tienda.models.facturas.Reportes; // Importar la clase correcta

public class ReportesRepositorio {
    private final Map<Integer, Reportes> reportes = new HashMap<>();

    public void agregar(Reportes r) {
        validar(r);
        if (reportes.containsKey(r.getId())) {
            throw new IllegalArgumentException("Ya existe un reporte con ese ID");
        }
        reportes.put(r.getId(), r);
    }

    public List<Reportes> obtenerTodos() {
        return new ArrayList<>(reportes.values());
    }

    public Reportes obtener(int id) {
        validarId(id);
        return reportes.get(id);
    }

    public Reportes actualizar(int id, Reportes r) {
        validarId(id);
        validar(r);
        reportes.put(id, r);
        return r;
    }

    public void eliminar(int id) {
        validarId(id);
        reportes.remove(id);
    }

    private void validar(Reportes r) {
        if (r == null || r.getNombre() == null || r.getContenido() == null) {
            throw new IllegalArgumentException("Datos inválidos del reporte");
        }
    }

    private void validarId(int id) {
        if (!reportes.containsKey(id)) {
            throw new IllegalArgumentException("Reporte no encontrado");
        }
    }
}