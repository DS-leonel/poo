package com.tienda.repositories.facturas;

import com.tienda.models.facturas.Pago;

import java.util.ArrayList;
import java.util.List;

public class PagoRepositorio {
    private final List<Pago> pagos = new ArrayList<>();

    public List<Pago> obtenerTodos() {
        return new ArrayList<>(pagos);
    }

    public void guardar(Pago pago) {
        pagos.add(pago);
    }

    public Pago buscarPorId(int id) {
        return pagos.stream()
                .filter(pago -> pago.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void eliminarPorId(int id) {
        pagos.removeIf(pago -> pago.getId() == id);
    }
}