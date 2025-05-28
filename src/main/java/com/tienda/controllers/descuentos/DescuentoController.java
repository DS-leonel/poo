package com.tienda.controllers.descuentos;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.tienda.models.descuentos.Descuento;
import com.tienda.repositories.descuentos.DescuentoRepositorio;

public class DescuentoController {
    static DescuentoRepositorio repositorio = new DescuentoRepositorio();
    static Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/descuentos", ctx -> {
            Descuento descuento = gson.fromJson(ctx.body(), Descuento.class);
            repositorio.agregar(descuento);
            ctx.status(201).json(descuento);
        });

        app.get("/descuentos", ctx -> ctx.json(repositorio.obtenerTodos()));

        app.get("/descuentos/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(repositorio.obtener(id));
        });

        app.put("/descuentos/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Descuento descuento = gson.fromJson(ctx.body(), Descuento.class);
            ctx.json(repositorio.actualizar(id, descuento));
        });

        app.delete("/descuentos/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Descuento eliminado");
        });
    }
}
