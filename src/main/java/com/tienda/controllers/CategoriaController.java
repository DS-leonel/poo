package com.tienda.controllers;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.tienda.models.Categoria;
import com.tienda.repositories.CategoriaRepositorio;

public class CategoriaController {
    static CategoriaRepositorio repositorio = new CategoriaRepositorio();
    static Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/categorias", ctx -> {
            Categoria categoria = gson.fromJson(ctx.body(), Categoria.class);
            repositorio.agregar(categoria);
            ctx.status(201).json(categoria);
        });

        app.get("/categorias", ctx -> ctx.json(repositorio.obtenerTodos()));

        app.get("/categorias/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(repositorio.obtener(id));
        });

        app.put("/categorias/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Categoria categoria = gson.fromJson(ctx.body(), Categoria.class);
            ctx.json(repositorio.actualizar(id, categoria));
        });

        app.delete("/categorias/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Categoría eliminada");
        });
    }
}
