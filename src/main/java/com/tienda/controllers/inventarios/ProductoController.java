package com.tienda.controllers.inventarios;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.tienda.models.inventarios.Producto;
import com.tienda.repositories.inventarios.ProductoRepositorio;

public class ProductoController {
    static ProductoRepositorio repositorio = new ProductoRepositorio();
    static Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/productos", ctx -> {
            Producto producto = gson.fromJson(ctx.body(), Producto.class);
            repositorio.agregar(producto);
            ctx.status(201).json(producto);
        });

        app.get("/productos", ctx -> ctx.json(repositorio.obtenerTodos()));

        app.get("/productos/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(repositorio.obtener(id));
        });

        app.put("/productos/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Producto producto = gson.fromJson(ctx.body(), Producto.class);
            ctx.json(repositorio.actualizar(id, producto));
        });

        app.delete("/productos/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Producto eliminado");
        });
    }
}
