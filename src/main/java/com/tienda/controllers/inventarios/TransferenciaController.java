package com.tienda.controllers.inventarios;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tienda.models.inventarios.Transferencia;
import com.tienda.repositories.inventarios.TransferenciaRepositorio;

public class TransferenciaController {
    static TransferenciaRepositorio repositorio = new TransferenciaRepositorio();
    static Gson gson = new Gson();

    public static void init(Javalin app) {

        app.post("/transferencias", ctx -> {
            try {
                Transferencia transferencia = gson.fromJson(ctx.body(), Transferencia.class);

                repositorio.agregar(transferencia);
                ctx.status(201).json(transferencia);
            } catch (JsonSyntaxException e) {
                ctx.status(400).result("JSON con formato incorrecto: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                ctx.status(400).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(500).result("Error interno al crear la transferencia: " + e.getMessage());
            }
        });

        app.get("/transferencias", ctx -> {
            try {
                ctx.json(repositorio.obtenerTodos());
            } catch (Exception e) {
                ctx.status(500).result("Error interno al obtener las transferencias: " + e.getMessage());
            }
        });

        app.get("/transferencias/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Transferencia transferencia = repositorio.obtener(id);
                ctx.json(transferencia);
            } catch (NumberFormatException e) {
                ctx.status(400).result("El ID de la transferencia debe ser un número entero.");
            } catch (IllegalArgumentException e) {
                ctx.status(404).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(500).result("Error interno al obtener la transferencia: " + e.getMessage());
            }
        });

        app.put("/transferencias/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Transferencia transferencia = gson.fromJson(ctx.body(), Transferencia.class);

                Transferencia actualizada = repositorio.actualizar(id, transferencia);
                ctx.json(actualizada);
            } catch (NumberFormatException e) {
                ctx.status(400).result("El ID de la transferencia debe ser un número entero.");
            } catch (JsonSyntaxException e) {
                ctx.status(400).result("JSON con formato incorrecto: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                if (e.getMessage() != null && e.getMessage().toLowerCase().contains("no encontrada")) {
                    ctx.status(404).result(e.getMessage());
                } else {
                    ctx.status(400).result(e.getMessage());
                }
            } catch (Exception e) {
                ctx.status(500).result("Error interno al actualizar la transferencia: " + e.getMessage());
            }
        });

        app.delete("/transferencias/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                repositorio.eliminar(id);
                ctx.status(200).result("Transferencia eliminada correctamente");
            } catch (NumberFormatException e) {
                ctx.status(400).result("El ID de la transferencia debe ser un número entero.");
            } catch (IllegalArgumentException e) {
                ctx.status(404).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(500).result("Error interno al eliminar la transferencia: " + e.getMessage());
            }
        });
    }
}