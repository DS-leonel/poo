package com.tienda.controllers.inventarios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tienda.models.inventarios.Envio;
import com.tienda.repositories.inventarios.EnvioRepositorio;
import com.tienda.util.LocalDateAdapter;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

import java.time.LocalDate;

public class EnvioController {
    private static final EnvioRepositorio repositorio = new EnvioRepositorio();
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public static void init(Javalin app) {
        app.get("/envios", ctx -> ctx.json(repositorio.obtenerTodos()));

        app.post("/envios", ctx -> {
            try {
                Envio envio = gson.fromJson(ctx.body(), Envio.class);
                repositorio.agregar(envio);
                ctx.status(HttpStatus.CREATED).json(envio);
            } catch (Exception e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("Error al agregar el envío: " + e.getMessage());
            }
        });

        app.get("/envios/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Envio envio = repositorio.obtener(id);
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
                boolean eliminado = repositorio.eliminar(id);
                if (eliminado) {
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
