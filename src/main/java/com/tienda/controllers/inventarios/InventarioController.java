package com.tienda.controllers.inventarios;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.tienda.models.inventarios.Inventario;
import com.tienda.repositories.inventarios.InventarioRepositorio;

public class InventarioController {
    static InventarioRepositorio repositorio = new InventarioRepositorio();
    static Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/inventarios", ctx -> {
            Inventario inventario = gson.fromJson(ctx.body(), Inventario.class);
            repositorio.agregar(inventario);
            ctx.status(201).json(inventario);
        });

        app.get("/inventarios", ctx -> ctx.json(repositorio.obtenerTodos()));

        app.get("/inventarios/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(repositorio.obtener(id));
        });

        app.put("/inventarios/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Inventario inventario = gson.fromJson(ctx.body(), Inventario.class);
            ctx.json(repositorio.actualizar(id, inventario));
        });

        app.delete("/inventarios/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Inventario eliminado");
        });
    }
}

