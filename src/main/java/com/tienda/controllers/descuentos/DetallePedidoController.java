package com.tienda.controllers.descuentos;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import com.google.gson.Gson;
import com.tienda.models.descuentos.DetallePedido;
import com.tienda.repositories.descuentos.DetallePedidoRepositorio;

public class DetallePedidoController {

    private static final DetallePedidoRepositorio repositorio = new DetallePedidoRepositorio();
    private static final Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/detalle-pedidos", DetallePedidoController::crearDetalle);
        app.get("/detalle-pedidos", ctx -> ctx.json(repositorio.obtenerTodos()));
        app.get("/detalle-pedidos/{id}", DetallePedidoController::obtenerDetalle);
        app.put("/detalle-pedidos/{id}", DetallePedidoController::actualizarDetalle);
        app.delete("/detalle-pedidos/{id}", DetallePedidoController::eliminarDetalle);
    }

    private static void crearDetalle(Context ctx) {
        try {
            DetallePedido detalle = gson.fromJson(ctx.body(), DetallePedido.class);
            repositorio.agregar(detalle);
            ctx.status(HttpStatus.CREATED).json(detalle);
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST).result(e.getMessage());
        }
    }

    private static void obtenerDetalle(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            DetallePedido detalle = repositorio.obtenerPorId(id);
            ctx.json(detalle);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }

    private static void actualizarDetalle(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            DetallePedido detalle = gson.fromJson(ctx.body(), DetallePedido.class);
            DetallePedido actualizado = repositorio.actualizar(id, detalle);
            ctx.json(actualizado);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }

    private static void eliminarDetalle(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Detalle eliminado");
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }
}
