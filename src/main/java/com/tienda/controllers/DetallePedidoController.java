package com.tienda.controllers;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.tienda.models.DetallePedido;
import com.tienda.repositories.DetallePedidoRepositorio;

public class DetallePedidoController {
    static DetallePedidoRepositorio repositorio = new DetallePedidoRepositorio();
    static Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/detalle-pedido", ctx -> {
            try {
                DetallePedido detalle = gson.fromJson(ctx.body(), DetallePedido.class);
                if (detalle == null || detalle.getId() == 0) {
                    ctx.status(400).result(gson.toJson("Error: Datos inválidos"));
                    return;
                }
                repositorio.agregar(detalle);
                ctx.status(201).result(gson.toJson(detalle));
            } catch (Exception e) {
                ctx.status(500).result(gson.toJson("Error interno del servidor"));
            }
        });

        app.get("/detalle-pedido", ctx ->
                ctx.contentType("application/json").result(gson.toJson(repositorio.obtenerTodos()))
        );

        app.get("/detalle-pedido/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                DetallePedido detalle = repositorio.obtener(id);
                if (detalle != null) {
                    ctx.contentType("application/json").result(gson.toJson(detalle));
                } else {
                    ctx.status(404).result(gson.toJson("DetallePedido no encontrado"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            }
        });

        app.put("/detalle-pedido/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                DetallePedido detalle = gson.fromJson(ctx.body(), DetallePedido.class);
                if (detalle == null) {
                    ctx.status(400).result(gson.toJson("Error: Datos inválidos"));
                    return;
                }
                DetallePedido detalleActualizado = repositorio.actualizar(id, detalle);
                if (detalleActualizado != null) {
                    ctx.contentType("application/json").result(gson.toJson(detalleActualizado));
                } else {
                    ctx.status(404).result(gson.toJson("DetallePedido no encontrado"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            } catch (Exception e) {
                ctx.status(500).result(gson.toJson("Error interno del servidor"));
            }
        });

        app.delete("/detalle-pedido/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                boolean eliminado = repositorio.eliminar(id);
                if (eliminado) {
                    ctx.status(200).result(gson.toJson("DetallePedido eliminado"));
                } else {
                    ctx.status(404).result(gson.toJson("DetallePedido no encontrado"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            }
        });
    }
}