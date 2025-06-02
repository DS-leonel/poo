package com.tienda.controllers.facturas;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import com.google.gson.Gson;
import com.tienda.models.facturas.Cliente;
import com.tienda.repositories.facturas.ClienteRepositorio;

public class ClienteController {
    private static final ClienteRepositorio repositorio = new ClienteRepositorio();
    private static final Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/clientes", ClienteController::crearCliente);
        app.get("/clientes", ctx -> ctx.json(repositorio.obtenerTodos()));
        app.get("/clientes/{id}", ClienteController::obtenerCliente);
        app.put("/clientes/{id}", ClienteController::actualizarCliente);
        app.delete("/clientes/{id}", ClienteController::eliminarCliente);
    }

    private static void crearCliente(Context ctx) {
        try {
            Cliente cliente = gson.fromJson(ctx.body(), Cliente.class);
            repositorio.agregar(cliente);
            ctx.status(HttpStatus.CREATED).json(cliente);
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("Error al crear cliente: " + e.getMessage());
        }
    }

    private static void obtenerCliente(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Cliente cliente = repositorio.obtener(id);
            ctx.json(cliente);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }

    private static void actualizarCliente(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Cliente cliente = gson.fromJson(ctx.body(), Cliente.class);
            Cliente actualizado = repositorio.actualizar(id, cliente);
            ctx.json(actualizado);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }

    private static void eliminarCliente(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Cliente eliminado");
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }
}
