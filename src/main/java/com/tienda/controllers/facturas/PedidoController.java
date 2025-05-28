package com.tienda.controllers.facturas;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.tienda.models.facturas.Pedido;
import com.tienda.repositories.facturas.PedidoRepositorio;

public class PedidoController {
    static PedidoRepositorio repositorio = new PedidoRepositorio();
    static Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/pedidos", ctx -> {
            Pedido pedido = gson.fromJson(ctx.body(), Pedido.class);
            repositorio.agregar(pedido);
            ctx.status(201).json(pedido);
        });

        app.get("/pedidos", ctx -> ctx.json(repositorio.obtenerTodos()));

        app.get("/pedidos/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(repositorio.obtener(id));
        });

        app.put("/pedidos/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Pedido pedido = gson.fromJson(ctx.body(), Pedido.class);
            ctx.json(repositorio.actualizar(id, pedido));
        });

        app.delete("/pedidos/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Pedido eliminado");
        });
    }
}
