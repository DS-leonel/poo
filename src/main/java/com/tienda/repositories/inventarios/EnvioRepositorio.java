package com.tienda.repositories.inventarios;

import com.tienda.models.inventarios.Envio;
import java.util.ArrayList;
import java.util.List;

public class EnvioRepositorio {
    private List<Envio> envios = new ArrayList<>();

    public List<Envio> obtenerTodos() {
        return envios;
    }

    public void guardar(Envio envio) {
        envios.add(envio);
    }

    public void eliminarPorId(int id) {
        envios.removeIf(e -> e.getId() == id);
    }

    public Envio buscarPorId(int id) {
        return envios.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
}