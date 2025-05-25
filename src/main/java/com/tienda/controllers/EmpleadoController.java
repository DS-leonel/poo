package com.tienda.controllers;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.tienda.models.Empleado;
import com.tienda.repositories.EmpleadoRepositorio;

public class EmpleadoController {
    static EmpleadoRepositorio repositorio = new EmpleadoRepositorio();
    static Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/empleados", ctx -> {
            try {
                Empleado empleado = gson.fromJson(ctx.body(), Empleado.class);
                if (empleado == null || empleado.getNombre() == null || empleado.getPuesto() == null) {
                    ctx.status(400).result(gson.toJson("Error: Datos inválidos"));
                    return;
                }
                repositorio.agregar(empleado);
                ctx.status(201).result(gson.toJson(empleado));
            } catch (Exception e) {
                ctx.status(500).result(gson.toJson("Error interno del servidor"));
            }
        });

        app.get("/empleados", ctx ->
                ctx.contentType("application/json").result(gson.toJson(repositorio.obtenerTodos()))
        );

        app.get("/empleados/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Empleado empleado = repositorio.obtener(id);
                if (empleado != null) {
                    ctx.contentType("application/json").result(gson.toJson(empleado));
                } else {
                    ctx.status(404).result(gson.toJson("Empleado no encontrado"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            }
        });

        app.put("/empleados/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Empleado empleado = gson.fromJson(ctx.body(), Empleado.class);
                if (empleado == null || empleado.getNombre() == null || empleado.getPuesto() == null) {
                    ctx.status(400).result(gson.toJson("Error: Datos inválidos"));
                    return;
                }
                Empleado empleadoActualizado = repositorio.actualizar(id, empleado);
                if (empleadoActualizado != null) {
                    ctx.contentType("application/json").result(gson.toJson(empleadoActualizado));
                } else {
                    ctx.status(404).result(gson.toJson("Empleado no encontrado"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            } catch (Exception e) {
                ctx.status(500).result(gson.toJson("Error interno del servidor"));
            }
        });

        app.delete("/empleados/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                boolean eliminado = repositorio.eliminar(id);
                if (eliminado) {
                    ctx.status(200).result(gson.toJson("Empleado eliminado"));
                } else {
                    ctx.status(404).result(gson.toJson("Empleado no encontrado"));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result(gson.toJson("Error: ID inválido"));
            }
        });
    }
}