package com.tienda.controllers.facturas;

import com.google.gson.Gson;
import com.tienda.models.facturas.Pago;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class PagoController {
    private static final List<Pago> pagos = new ArrayList<>();
    private static final Gson gson = new Gson();

    public static void init(Javalin app) {
        app.get("/pagos", ctx -> {
            ctx.json(pagos);
        });

        app.post("/pagos", ctx -> {
            try {
                Pago pago = gson.fromJson(ctx.body(), Pago.class);
                pagos.add(pago);
                ctx.status(HttpStatus.CREATED).json(pago);
            } catch (Exception e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("Error al agregar el pago.");
            }
        });

        app.get("/pagos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Pago pago = pagos.stream()
                        .filter(p -> p.getId() == id)
                        .findFirst()
                        .orElse(null);
                if (pago != null) {
                    ctx.json(pago);
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Pago no encontrado.");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            }
        });

        app.delete("/pagos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                boolean removed = pagos.removeIf(p -> p.getId() == id);
                if (removed) {
                    ctx.status(HttpStatus.OK).result("Pago eliminado correctamente.");
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Pago no encontrado.");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            }
        });
    }
}