package com.tienda.controllers;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.tienda.models.Pedido;
import com.tienda.repositories.PedidoRepositorio;

public class PedidoController {
    static PedidoRepositorio repositorio = new PedidoRepositorio();
    static Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/pedidos", ctx -> {
            try {
                Pedido pedido = gson.fromJson(ctx.body(), Pedido.class);
                if (pedido == null || pedido.getId() == null) {
                    ctx.status(400).result(gson.toJson("Error: Datos inválidos"));
                    return;
                }
                repositorio.agregar(pedido);
                ctx.status(201).result(gson.toJson(pedido));
            } catch (Exception e) {
                ctx.status(500).result(gson.toJson("Error interno del servidor"));
            }
        });

        app.get("/pedidos", ctx ->
                ctx.contentType("application/json").result(gson.toJson(repositorio.obtenerTodos()))
        );

        app.get("/pedidos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Pedido pedido = repositorio.obtener(id);
                if (pedido != null) {
                    ctx.contentType("application/json").result(gson.toJson(pedido));
                } else {
                    ctx.status(404).result(gson.toJson("Pedido no encontrado"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            }
        });

        app.put("/pedidos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Pedido pedido = gson.fromJson(ctx.body(), Pedido.class);
                if (pedido == null) {
                    ctx.status(400).result(gson.toJson("Error: Datos inválidos"));
                    return;
                }
                Pedido pedidoActualizado = repositorio.actualizar(id, pedido);
                if (pedidoActualizado != null) {
                    ctx.contentType("application/json").result(gson.toJson(pedidoActualizado));
                } else {
                    ctx.status(404).result(gson.toJson("Pedido no encontrado"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            } catch (Exception e) {
                ctx.status(500).result(gson.toJson("Error interno del servidor"));
            }
        });

        app.delete("/pedidos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                boolean eliminado = repositorio.eliminar(id);
                if (eliminado) {
                    ctx.status(200).result(gson.toJson("Pedido eliminado"));
                } else {
                    ctx.status(404).result(gson.toJson("Pedido no encontrado"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            }
        });
    }
}