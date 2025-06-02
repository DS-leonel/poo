package com.tienda.controllers.facturas;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.tienda.models.facturas.Reportes; // Importar la clase correcta
import com.tienda.repositories.facturas.ReportesRepositorio;

public class ReportesController {
    static ReportesRepositorio repositorio = new ReportesRepositorio();
    static Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/reportes", ctx -> {
            Reportes reporte = gson.fromJson(ctx.body(), Reportes.class);
            repositorio.agregar(reporte);
            ctx.status(201).json(reporte);
        });

        app.get("/reportes", ctx -> ctx.json(repositorio.obtenerTodos()));

        app.get("/reportes/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(repositorio.obtener(id));
        });

        app.put("/reportes/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Reportes reporte = gson.fromJson(ctx.body(), Reportes.class);
            ctx.json(repositorio.actualizar(id, reporte));
        });

        app.delete("/reportes/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Reporte eliminado");
        });
    }
}