package com.tienda.controllers.inventarios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tienda.models.inventarios.Lote;
import com.tienda.repositories.inventarios.LoteRepositorio;
import com.tienda.util.LocalDateAdapter;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

import java.time.LocalDate;

public class LoteController {
    private static final LoteRepositorio repositorio = new LoteRepositorio();

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    public static void init(Javalin app) {

        app.post("/lotes", ctx -> {
            try {
                Lote lote = gson.fromJson(ctx.body(), Lote.class);
                repositorio.agregar(lote);
                ctx.status(HttpStatus.CREATED).json(lote);
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al agregar el lote: " + e.getMessage());
                e.printStackTrace();
            }
        });

        app.get("/lotes", ctx -> {
            try {

                String productoIdParam = ctx.queryParam("productoId");
                if (productoIdParam != null) {
                    int productoId = Integer.parseInt(productoIdParam);
                    ctx.json(repositorio.obtenerPorProductoId(productoId));
                } else {
                    ctx.json(repositorio.obtenerTodos());
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El productoId debe ser un número entero.");
            } catch (IllegalArgumentException e) { // Para el caso de productoId <= 0 desde el repo
                ctx.status(HttpStatus.BAD_REQUEST).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al obtener los lotes.");
            }
        });

        app.get("/lotes/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Lote lote = repositorio.obtener(id);
                ctx.json(lote);
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al obtener el lote.");
            }
        });

        app.put("/lotes/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Lote lote = gson.fromJson(ctx.body(), Lote.class);
                Lote loteActualizado = repositorio.actualizar(id, lote);
                ctx.json(loteActualizado);
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            } catch (IllegalArgumentException e) {
                if (e.getMessage().contains("no encontrado")) {
                    ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
                } else {
                    ctx.status(HttpStatus.BAD_REQUEST).result(e.getMessage());
                }
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al actualizar el lote: " + e.getMessage());
                e.printStackTrace();
            }
        });

        app.delete("/lotes/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                repositorio.eliminar(id);
                ctx.status(HttpStatus.OK).result("Lote eliminado correctamente.");
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al eliminar el lote.");
            }
        });
    }
}