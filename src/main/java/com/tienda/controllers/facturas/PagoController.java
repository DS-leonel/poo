package com.tienda.controllers.facturas;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import com.google.gson.Gson;
import com.tienda.models.facturas.Pago;
import com.tienda.repositories.facturas.PagoRepositorio;

public class PagoController {
    private final PagoRepositorio repositorio;
    private final Gson gson;

    public PagoController() {
        this.repositorio = new PagoRepositorio();
        this.gson = new Gson();
    }

    public void init(Javalin app) {
        app.get("/pagos", ctx -> ctx.json(repositorio.obtenerTodos()));

        app.post("/pagos", ctx -> {
            try {
                Pago nuevoPago = gson.fromJson(ctx.body(), Pago.class);
                repositorio.guardar(nuevoPago);
                ctx.status(HttpStatus.CREATED).json(nuevoPago);
            } catch (Exception e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("Error al agregar el pago: " + e.getMessage());
            }
        });

        app.get("/pagos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Pago pago = repositorio.buscarPorId(id);
                if (pago != null) {
                    ctx.json(pago);
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Pago no encontrado");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero");
            }
        });

        app.put("/pagos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Pago pagoActualizado = gson.fromJson(ctx.body(), Pago.class);
                Pago pago = repositorio.actualizar(id, pagoActualizado);
                ctx.json(pago);
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero");
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("Error al actualizar el pago: " + e.getMessage());
            }
        });

        app.delete("/pagos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                boolean eliminado = repositorio.eliminarPorId(id);
                if (eliminado) {
                    ctx.status(HttpStatus.OK).result("Pago eliminado correctamente");
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Pago no encontrado");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero");
            }
        });
    }
}
