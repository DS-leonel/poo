package com.tienda.controllers;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.tienda.models.Cliente;
import com.tienda.repositories.ClienteRepositorio;

public class ClienteController {
    static ClienteRepositorio repositorio = new ClienteRepositorio();
    static Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/clientes", ctx -> {
            Cliente cliente = gson.fromJson(ctx.body(), Cliente.class);
            repositorio.agregar(cliente);
            ctx.status(201).json(cliente);
        });

        app.get("/clientes", ctx -> ctx.json(repositorio.obtenerTodos()));

        app.get("/clientes/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(repositorio.obtener(id));
        });

        app.put("/clientes/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Cliente cliente = gson.fromJson(ctx.body(), Cliente.class);
            ctx.json(repositorio.actualizar(id, cliente));
        });

        app.delete("/clientes/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Cliente eliminado");
        });
    }
}
