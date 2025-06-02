package com.tienda.controllers.facturas;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import com.google.gson.Gson;
import com.tienda.models.facturas.Factura;
import com.tienda.repositories.facturas.FacturaRepositorio;

public class FacturaController {
    private static final FacturaRepositorio repositorio = new FacturaRepositorio();
    private static final Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/facturas", FacturaController::crearFactura);
        app.get("/facturas", ctx -> ctx.json(repositorio.obtenerTodos()));
        app.get("/facturas/{id}", FacturaController::obtenerFactura);
        app.put("/facturas/{id}", FacturaController::actualizarFactura);
        app.delete("/facturas/{id}", FacturaController::eliminarFactura);
    }

    private static void crearFactura(Context ctx) {
        try {
            Factura factura = gson.fromJson(ctx.body(), Factura.class);
            repositorio.agregar(factura);
            ctx.status(HttpStatus.CREATED).json(factura);
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("Error al crear factura: " + e.getMessage());
        }
    }

    private static void obtenerFactura(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Factura factura = repositorio.obtener(id);
            ctx.json(factura);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }

    private static void actualizarFactura(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Factura factura = gson.fromJson(ctx.body(), Factura.class);
            Factura actualizado = repositorio.actualizar(id, factura);
            ctx.json(actualizado);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }

    private static void eliminarFactura(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Factura eliminada");
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }
}
