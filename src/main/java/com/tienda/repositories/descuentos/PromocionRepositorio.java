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
        promociones.add(promocion);
    }

    public void eliminarPorId(int id) {
        promociones.removeIf(p -> p.getId() == id);
    }

    public Promocion buscarPorId(int id) {
        return promociones.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}