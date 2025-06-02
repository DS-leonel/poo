package com.tienda.controllers.descuentos;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import com.google.gson.Gson;
import com.tienda.models.descuentos.Descuento;
import com.tienda.repositories.descuentos.DescuentoRepositorio;

public class DescuentoController {

    private static final DescuentoRepositorio repositorio = new DescuentoRepositorio();
    private static final Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/descuentos", DescuentoController::crearDescuento);
        app.get("/descuentos", ctx -> ctx.json(repositorio.obtenerTodos()));
        app.get("/descuentos/{id}", DescuentoController::obtenerDescuento);
        app.put("/descuentos/{id}", DescuentoController::actualizarDescuento);
        app.delete("/descuentos/{id}", DescuentoController::eliminarDescuento);
    }

    private static void crearDescuento(Context ctx) {
        try {
            Descuento descuento = gson.fromJson(ctx.body(), Descuento.class);
            repositorio.agregar(descuento);
            ctx.status(HttpStatus.CREATED).json(descuento);
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST).result(e.getMessage());
        }
    }

    private static void obtenerDescuento(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(repositorio.obtener(id));
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }

    private static void actualizarDescuento(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Descuento descuento = gson.fromJson(ctx.body(), Descuento.class);
            ctx.json(repositorio.actualizar(id, descuento));
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }

    private static void eliminarDescuento(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Descuento eliminado");
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }
}
