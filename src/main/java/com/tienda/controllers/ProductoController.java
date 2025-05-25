package com.tienda.controllers;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tienda.models.Producto;
import com.tienda.repositories.ProductoRepositorio;

public class ProductoController {
    static ProductoRepositorio repositorio = new ProductoRepositorio();
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void init(Javalin app) {
        app.post("/productos", ctx -> {
            try {
                Producto producto = gson.fromJson(ctx.body(), Producto.class);
                if (producto == null) {
                    ctx.status(400).result(gson.toJson("Error: Datos inválidos"));
                    return;
                }
                repositorio.agregar(producto);
                ctx.status(201).result(gson.toJson(producto));
            } catch (Exception e) {
                ctx.status(500).result(gson.toJson("Error interno del servidor"));
            }
        });

        app.get("/productos", ctx ->
                ctx.contentType("application/json").result(gson.toJson(repositorio.obtenerTodos()))
        );

        app.get("/productos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Producto producto = repositorio.obtener(id);
                if (producto != null) {
                    ctx.contentType("application/json").result(gson.toJson(producto));
                } else {
                    ctx.status(404).result(gson.toJson("Producto no encontrado"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            }
        });

        app.put("/productos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Producto producto = gson.fromJson(ctx.body(), Producto.class);
                if (producto == null) {
                    ctx.status(400).result(gson.toJson("Error: Datos inválidos"));
                    return;
                }
                Producto productoActualizado = repositorio.actualizar(id, producto);
                if (productoActualizado != null) {
                    ctx.contentType("application/json").result(gson.toJson(productoActualizado));
                } else {
                    ctx.status(404).result(gson.toJson("Producto no encontrado"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            } catch (Exception e) {
                ctx.status(500).result(gson.toJson("Error interno del servidor"));
            }
        });

        app.delete("/productos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                boolean eliminado = repositorio.eliminar(id);
                if (eliminado) {
                    ctx.status(200).result(gson.toJson("Producto eliminado"));
                } else {
                    ctx.status(404).result(gson.toJson("Producto no encontrado"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            }
        });
    }
}