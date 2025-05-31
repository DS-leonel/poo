package com.tienda.controllers.descuentos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tienda.models.descuentos.Promocion;
import com.tienda.repositories.descuentos.PromocionRepositorio;
import com.tienda.util.LocalDateAdapter;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

import java.time.LocalDate;

public class PromocionController {
    private static final PromocionRepositorio repositorio = new PromocionRepositorio();
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    public static void init(Javalin app) {
        app.get("/promociones", ctx -> {
            ctx.json(repositorio.obtenerTodos());
        });

        app.post("/promociones", ctx -> {
            try {
                Promocion promocion = gson.fromJson(ctx.body(), Promocion.class);
                repositorio.guardar(promocion);
                ctx.status(HttpStatus.CREATED).json(promocion);
            } catch (Exception e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("Error al agregar la promoción: " + e.getMessage());
            }
        });

        app.get("/promociones/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Promocion promocion = repositorio.buscarPorId(id);
                if (promocion != null) {
                    ctx.json(promocion);
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Promoción no encontrada.");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            }
        });

        app.delete("/promociones/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                repositorio.eliminarPorId(id);
                ctx.status(HttpStatus.OK).result("Promoción eliminada correctamente.");
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            }
        });
    }
}