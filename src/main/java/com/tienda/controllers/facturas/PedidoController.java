package com.tienda.controllers.facturas;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import com.google.gson.Gson;
import com.tienda.models.facturas.Pedido;
import com.tienda.repositories.facturas.PedidoRepositorio;

public class PedidoController {
    private static final PedidoRepositorio repositorio = new PedidoRepositorio();
    private static final Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/pedidos", ctx -> {
            try {
                Pedido pedido = gson.fromJson(ctx.body(), Pedido.class);
                repositorio.agregar(pedido);
                ctx.status(HttpStatus.CREATED).json(pedido);
            } catch (Exception e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("Error al agregar el pedido: " + e.getMessage());
            }
        });

        app.get("/pedidos", ctx -> ctx.json(repositorio.obtenerTodos()));

        app.get("/pedidos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Pedido pedido = repositorio.obtener(id);
                if (pedido != null) {
                    ctx.json(pedido);
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Pedido no encontrado");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero");
            }
        });

        app.put("/pedidos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Pedido pedidoActualizado = gson.fromJson(ctx.body(), Pedido.class);
                Pedido pedido = repositorio.actualizar(id, pedidoActualizado);
                if (pedido != null) {
                    ctx.json(pedido);
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Pedido no encontrado");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero");
            } catch (Exception e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("Error al actualizar el pedido: " + e.getMessage());
            }
        });

        app.delete("/pedidos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Pedido pedido = repositorio.obtener(id);
                if (pedido != null) {
                    repositorio.eliminar(id);
                    ctx.status(HttpStatus.OK).result("Pedido eliminado correctamente");
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Pedido no encontrado");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero");
            }
        });
    }
}
