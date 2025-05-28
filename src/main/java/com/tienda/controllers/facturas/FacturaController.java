package com.tienda.controllers.facturas;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.tienda.models.facturas.Factura;
import com.tienda.repositories.facturas.FacturaRepositorio;

public class FacturaController {
    static FacturaRepositorio repositorio = new FacturaRepositorio();
    static Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/facturas", ctx -> {
            Factura factura = gson.fromJson(ctx.body(), Factura.class);
            repositorio.agregar(factura);
            ctx.status(201).json(factura);
        });

        app.get("/facturas", ctx -> ctx.json(repositorio.obtenerTodos()));

        app.get("/facturas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(repositorio.obtener(id));
        });

        app.put("/facturas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Factura factura = gson.fromJson(ctx.body(), Factura.class);
            ctx.json(repositorio.actualizar(id, factura));
        });

        app.delete("/facturas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Factura eliminada");
        });
    }
}

