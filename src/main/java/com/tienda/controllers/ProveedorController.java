package com.tienda.controllers;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.tienda.models.Proveedor;
import com.tienda.repositories.ProveedorRepositorio;

public class ProveedorController {
    static ProveedorRepositorio repositorio = new ProveedorRepositorio();
    static Gson gson = new Gson();
    public static void init(Javalin app) {

        app.post("/proveedores", ctx -> {
            try {
                Proveedor proveedor = gson.fromJson(ctx.body(), Proveedor.class);
                repositorio.agregar(proveedor);
                ctx.status(201).json(proveedor);
            } catch (IllegalArgumentException e) {
                ctx.status(400).result(e.getMessage());
            } catch (Exception e) { // Captura más genérica para otros errores
                ctx.status(500).result("Error interno al crear el proveedor");
            }
        });


        app.get("/proveedores", ctx -> {
            try {
                ctx.json(repositorio.obtenerTodos());
            } catch (Exception e) {
                ctx.status(500).result("Error interno al obtener los proveedores");
            }
        });


        app.get("/proveedores/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Proveedor proveedor = repositorio.obtener(id);
                if (proveedor != null) {
                    ctx.json(proveedor);
                } else {
                    ctx.status(404).result("Proveedor no encontrado");
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result("El ID del proveedor debe ser un número entero.");
            } catch (IllegalArgumentException e) {
                ctx.status(404).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(500).result("Error interno al obtener el proveedor");
            }
        });


        app.put("/proveedores/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Proveedor proveedor = gson.fromJson(ctx.body(), Proveedor.class);
                Proveedor actualizado = repositorio.actualizar(id, proveedor);
                if (actualizado != null) {
                    ctx.json(actualizado);
                } else {

                    ctx.status(404).result("Proveedor no encontrado para actualizar");
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result("El ID del proveedor debe ser un número entero.");
            } catch (IllegalArgumentException e) { // Captura validaciones del modelo o repositorio
                ctx.status(400).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(500).result("Error interno al actualizar el proveedor");
            }
        });


        app.delete("/proveedores/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                repositorio.eliminar(id);
                ctx.status(200).result("Proveedor eliminado correctamente");
            } catch (NumberFormatException e) {
                ctx.status(400).result("El ID del proveedor debe ser un número entero.");
            } catch (IllegalArgumentException e) {
                ctx.status(404).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(500).result("Error interno al eliminar el proveedor");
            }
        });
    }
}