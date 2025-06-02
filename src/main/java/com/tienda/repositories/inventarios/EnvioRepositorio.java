package com.tienda.repositories.inventarios;

import com.tienda.models.inventarios.Envio;

import java.util.ArrayList;
import java.util.List;

public class EnvioRepositorio {
    private final List<Envio> envios = new ArrayList<>();

    public List<Envio> obtenerTodos() {
        return new ArrayList<>(envios);
    }

    public void agregar(Envio envio) {
        envios.add(envio);
    }

    public Envio obtener(int id) {
        return envios.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean eliminar(int id) {
        return envios.removeIf(e -> e.getId() == id);
    }
}
