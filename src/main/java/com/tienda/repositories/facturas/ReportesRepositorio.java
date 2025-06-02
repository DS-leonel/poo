package com.tienda.repositories.facturas;

import java.util.*;
import com.tienda.models.facturas.Reportes;

public class ReportesRepositorio {
    private final Map<Integer, Reportes> reportes = new HashMap<>();

    public void agregar(Reportes reporte) {
        validar(reporte);
        if (reportes.containsKey(reporte.getId())) {
            throw new IllegalArgumentException("Ya existe un reporte con ese ID: " + reporte.getId());
        }
        reportes.put(reporte.getId(), reporte);
    }

    public List<Reportes> obtenerTodos() {
        return new ArrayList<>(reportes.values());
    }

    public Reportes obtener(int id) {
        validarId(id);
        return reportes.get(id);
    }

    public Reportes actualizar(int id, Reportes reporte) {
        validarId(id);
        validar(reporte);
        reportes.put(id, reporte);
        return reporte;
    }

    public void eliminar(int id) {
        validarId(id);
        reportes.remove(id);
    }

    private void validar(Reportes reporte) {
        if (reporte == null) {
            throw new IllegalArgumentException("El reporte no puede ser nulo.");
        }
        if (reporte.getNombre() == null || reporte.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del reporte es obligatorio.");
        }
        if (reporte.getContenido() == null || reporte.getContenido().trim().isEmpty()) {
            throw new IllegalArgumentException("El contenido del reporte es obligatorio.");
        }
    }

    private void validarId(int id) {
        if (!reportes.containsKey(id)) {
            throw new IllegalArgumentException("Reporte no encontrado con ID: " + id);
        }
    }
}
