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
            try {
                Categoria categoria = gson.fromJson(ctx.body(), Categoria.class);
                if (categoria == null) {
                    ctx.status(400).result(gson.toJson("Error: Datos inválidos"));
                    return;
                }
                repositorio.agregar(categoria);
                ctx.status(201).result(gson.toJson(categoria));
            } catch (Exception e) {
                ctx.status(500).result(gson.toJson("Error interno del servidor"));
            }
        });

        app.get("/categorias", ctx ->
                ctx.contentType("application/json").result(gson.toJson(repositorio.obtenerTodos()))
        );

        app.get("/categorias/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Categoria categoria = repositorio.obtener(id);
                if (categoria != null) {
                    ctx.contentType("application/json").result(gson.toJson(categoria));
                } else {
                    ctx.status(404).result(gson.toJson("Categoría no encontrada"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            }
        });

        app.put("/categorias/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Categoria categoria = gson.fromJson(ctx.body(), Categoria.class);
                if (categoria == null) {
                    ctx.status(400).result(gson.toJson("Error: Datos inválidos"));
                    return;
                }
                Categoria categoriaActualizada = repositorio.actualizar(id, categoria);
                if (categoriaActualizada != null) {
                    ctx.contentType("application/json").result(gson.toJson(categoriaActualizada));
                } else {
                    ctx.status(404).result(gson.toJson("Categoría no encontrada"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            } catch (Exception e) {
                ctx.status(500).result(gson.toJson("Error interno del servidor"));
            }
        });

        app.delete("/categorias/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                boolean eliminado = repositorio.eliminar(id);
                if (eliminado) {
                    ctx.status(200).result(gson.toJson("Categoría eliminada"));
                } else {
                    ctx.status(404).result(gson.toJson("Categoría no encontrada"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            }
        });
    }
}