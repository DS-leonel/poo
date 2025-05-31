package com.tienda.controllers.inventarios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tienda.models.inventarios.Envio;
import com.tienda.util.LocalDateAdapter; // Importación agregada
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnvioController {
    private static final List<Envio> envios = new ArrayList<>();
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    public static void init(Javalin app) {
        app.get("/envios", ctx -> {
            ctx.json(envios);
        });

        app.post("/envios", ctx -> {
            try {
                Envio envio = gson.fromJson(ctx.body(), Envio.class);
                envios.add(envio);
                ctx.status(HttpStatus.CREATED).json(envio);
            } catch (Exception e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("Error al agregar el envío.");
            }
        });

        app.get("/envios/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Envio envio = envios.stream()
                        .filter(e -> e.getId() == id)
                        .findFirst()
                        .orElse(null);
                if (envio != null) {
                    ctx.json(envio);
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Envío no encontrado.");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            }
        });

        app.delete("/envios/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                boolean removed = envios.removeIf(e -> e.getId() == id);
                if (removed) {
                    ctx.status(HttpStatus.OK).result("Envío eliminado correctamente.");
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Envío no encontrado.");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            }
        });
    }
}