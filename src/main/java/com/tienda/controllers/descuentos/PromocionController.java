package com.tienda.controllers.descuentos;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tienda.models.descuentos.Promocion;
import com.tienda.repositories.descuentos.PromocionRepositorio;
import com.tienda.util.LocalDateAdapter;

import java.time.LocalDate;

public class PromocionController {

    private static final PromocionRepositorio repositorio = new PromocionRepositorio();

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    public static void init(Javalin app) {
        app.get("/promociones", PromocionController::obtenerTodas);
        app.post("/promociones", PromocionController::crearPromocion);
        app.get("/promociones/{id}", PromocionController::obtenerPorId);
        app.delete("/promociones/{id}", PromocionController::eliminarPorId);
    }

    private static void obtenerTodas(Context ctx) {
        ctx.json(repositorio.obtenerTodos());
    }

    private static void crearPromocion(Context ctx) {
        try {
            Promocion promocion = gson.fromJson(ctx.body(), Promocion.class);
            repositorio.guardar(promocion);
            ctx.status(HttpStatus.CREATED).json(promocion);
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("Error al agregar la promoción: " + e.getMessage());
        }
    }

    private static void obtenerPorId(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Promocion promocion = repositorio.buscarPorId(id);
            if (promocion != null) {
                ctx.json(promocion);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("Promoción no encontrada.");
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        }
    }

    private static void eliminarPorId(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminarPorId(id);
            ctx.status(HttpStatus.OK).result("Promoción eliminada correctamente.");
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        }
    }
}
