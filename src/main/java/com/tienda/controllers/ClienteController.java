package com.tienda.controllers;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.tienda.models.Cliente;
import com.tienda.repositories.ClienteRepositorio;

public class ClienteController {
    static ClienteRepositorio repositorio = new ClienteRepositorio();
    static Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/clientes", ctx -> {
            try {
                Cliente cliente = gson.fromJson(ctx.body(), Cliente.class);
                if (cliente == null) {
                    ctx.status(400).result(gson.toJson("Error: Datos inválidos"));
                    return;
                }
                repositorio.agregar(cliente);
                ctx.status(201).result(gson.toJson(cliente));
            } catch (Exception e) {
                ctx.status(500).result(gson.toJson("Error interno del servidor"));
            }
        });

        app.get("/clientes", ctx ->
                ctx.contentType("application/json").result(gson.toJson(repositorio.obtenerTodos()))
        );

        app.get("/clientes/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Cliente cliente = repositorio.obtener(id);
                if (cliente != null) {
                    ctx.contentType("application/json").result(gson.toJson(cliente));
                } else {
                    ctx.status(404).result(gson.toJson("Cliente no encontrado"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            }
        });

        app.put("/clientes/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Cliente cliente = gson.fromJson(ctx.body(), Cliente.class);
                if (cliente == null) {
                    ctx.status(400).result(gson.toJson("Error: Datos inválidos"));
                    return;
                }
                Cliente clienteActualizado = repositorio.actualizar(id, cliente);
                if (clienteActualizado != null) {
                    ctx.contentType("application/json").result(gson.toJson(clienteActualizado));
                } else {
                    ctx.status(404).result(gson.toJson("Cliente no encontrado"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            } catch (Exception e) {
                ctx.status(500).result(gson.toJson("Error interno del servidor"));
            }
        });

        app.delete("/clientes/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                boolean eliminado = repositorio.eliminar(id);
                if (eliminado) {
                    ctx.status(200).result(gson.toJson("Cliente eliminado"));
                } else {
                    ctx.status(404).result(gson.toJson("Cliente no encontrado"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            }
        });
    }
}