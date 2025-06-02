package com.tienda.controllers.facturas;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import com.google.gson.Gson;
import com.tienda.models.facturas.Reportes;
import com.tienda.repositories.facturas.ReportesRepositorio;

public class ReportesController {
    private static final ReportesRepositorio repositorio = new ReportesRepositorio();
    private static final Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/reportes", ctx -> {
            try {
                Reportes reporte = gson.fromJson(ctx.body(), Reportes.class);
                repositorio.agregar(reporte);
                ctx.status(HttpStatus.CREATED).json(reporte);
            } catch (Exception e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("Error al crear el reporte: " + e.getMessage());
            }
        });

        app.get("/reportes", ctx -> ctx.json(repositorio.obtenerTodos()));

        app.get("/reportes/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Reportes reporte = repositorio.obtener(id);
                if (reporte != null) {
                    ctx.json(reporte);
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Reporte no encontrado");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero");
            }
        });

        app.put("/reportes/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Reportes reporteActualizado = gson.fromJson(ctx.body(), Reportes.class);
                Reportes reporte = repositorio.actualizar(id, reporteActualizado);
                if (reporte != null) {
                    ctx.json(reporte);
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Reporte no encontrado");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero");
            } catch (Exception e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("Error al actualizar el reporte: " + e.getMessage());
            }
        });

        app.delete("/reportes/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Reportes reporte = repositorio.obtener(id);
                if (reporte != null) {
                    repositorio.eliminar(id);
                    ctx.status(HttpStatus.OK).result("Reporte eliminado correctamente");
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Reporte no encontrado");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero");
            }
        });
    }
}
