package com.tienda.repositories.descuentos;

import com.tienda.models.descuentos.Promocion;
import java.util.ArrayList;
import java.util.List;

public class PromocionRepositorio {
    private final List<Promocion> promociones = new ArrayList<>();

    public List<Promocion> obtenerTodos() {
        return new ArrayList<>(promociones);
    }

    public void guardar(Promocion promocion) {
        validarPromocion(promocion);
        if (buscarPorId(promocion.getId()) != null) {
            throw new IllegalArgumentException("Ya existe una promoción con ID: " + promocion.getId());
        }
        promociones.add(promocion);
    }

    public void eliminarPorId(int id) {
        validarId(id);
        boolean removed = promociones.removeIf(p -> p.getId() == id);
        if (!removed) {
            throw new IllegalArgumentException("No se encontró una promoción con ID: " + id);
        }
    }

    public Promocion buscarPorId(int id) {
        validarId(id);
        return promociones.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private void validarPromocion(Promocion promocion) {
        if (promocion == null) {
            throw new IllegalArgumentException("La promoción no puede ser nula.");
        }
        if (promocion.getId() <= 0) {
            throw new IllegalArgumentException("El ID de la promoción debe ser mayor que cero.");
        }
    }

    private void validarId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
    }
}
