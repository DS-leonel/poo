package com.tienda.controllers.facturas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tienda.models.facturas.Devolucion;
import com.tienda.repositories.facturas.DevolucionRepositorio;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class DevolucionController {
    private static final DevolucionRepositorio repositorio = new DevolucionRepositorio();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void init(Javalin app) {

        app.post("/devoluciones", DevolucionController::crearDevolucion);
        app.get("/devoluciones", DevolucionController::obtenerTodasLasDevoluciones);
        app.get("/devoluciones/{id}", DevolucionController::obtenerDevolucionPorId);
        app.put("/devoluciones/{id}", DevolucionController::actualizarDevolucion);
        app.delete("/devoluciones/{id}", DevolucionController::eliminarDevolucion);
    }

    private static void crearDevolucion(Context ctx) {
        try {
            Devolucion devolucion = gson.fromJson(ctx.body(), Devolucion.class);
            repositorio.agregar(devolucion);
            ctx.status(201).json(devolucion);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error interno al crear la devolución: " + e.getMessage());
        }
    }

    private static void obtenerTodasLasDevoluciones(Context ctx) {
        try {
            ctx.json(repositorio.obtenerTodos());
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error interno al obtener las devoluciones: " + e.getMessage());
        }
    }

    private static void obtenerDevolucionPorId(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Devolucion devolucion = repositorio.obtener(id);
            ctx.json(devolucion);
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID de la devolución debe ser un número entero.");
        } catch (IllegalArgumentException e) {
            ctx.status(404).result(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error interno al obtener la devolución: " + e.getMessage());
        }
    }

    private static void actualizarDevolucion(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Devolucion devolucion = gson.fromJson(ctx.body(), Devolucion.class);
            Devolucion actualizada = repositorio.actualizar(id, devolucion);
            ctx.json(actualizada);
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID de la devolución debe ser un número entero.");
        } catch (IllegalArgumentException e) {
            ctx.status(e.getMessage().toLowerCase().contains("no encontrada") ? 404 : 400).result(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error interno al actualizar la devolución: " + e.getMessage());
        }
    }

    private static void eliminarDevolucion(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.status(200).result("Devolución eliminada con ID: " + id);
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID de la devolución debe ser un número entero.");
        } catch (IllegalArgumentException e) {
            ctx.status(404).result(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error interno al eliminar la devolución: " + e.getMessage());
        }
    }
}