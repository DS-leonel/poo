package com.tienda.controllers;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.tienda.models.DetallePedido;
import com.tienda.repositories.DetallePedidoRepositorio;

public class DetallePedidoController {
    static DetallePedidoRepositorio repositorio = new DetallePedidoRepositorio();
    static Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/detalle-pedidos", ctx -> {
            DetallePedido detalle = gson.fromJson(ctx.body(), DetallePedido.class);
            repositorio.agregar(detalle);
            ctx.status(201).json(detalle);
        });

        app.get("/detalle-pedidos", ctx -> ctx.json(repositorio.obtenerTodos()));

        app.get("/detalle-pedidos/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(repositorio.obtener(id));
        });

        app.put("/detalle-pedidos/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            DetallePedido detalle = gson.fromJson(ctx.body(), DetallePedido.class);
            ctx.json(repositorio.actualizar(id, detalle));
        });

        app.delete("/detalle-pedidos/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Detalle eliminado");
        });
    }
}