package com.tienda.controllers.seguridad;

import com.google.gson.Gson;
import com.tienda.models.Seguridad.Rol;
import com.tienda.repositories.Seguridad.RolRepositorio;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

public class RolController {
    private static final RolRepositorio repositorio = new RolRepositorio();
    private static final Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/roles", ctx -> {
            try {
                Rol rol = gson.fromJson(ctx.body(), Rol.class);
                repositorio.agregar(rol);
                ctx.status(HttpStatus.CREATED).json(rol);
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al agregar el rol.");
            }
        });

        app.get("/roles", ctx -> {
            try {
                ctx.json(repositorio.obtenerTodos());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al obtener los roles.");
            }
        });

        app.get("/roles/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Rol rol = repositorio.obtener(id);
                if (rol != null) {
                    ctx.json(rol);
                } else {

                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al obtener el rol.");
            }
        });

        app.put("/roles/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Rol rol = gson.fromJson(ctx.body(), Rol.class);

                Rol rolActualizado = repositorio.actualizar(id, rol);
                ctx.json(rolActualizado);
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            } catch (IllegalArgumentException e) {

                if (e.getMessage().contains("no encontrado")) {
                    ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
                } else {
                    ctx.status(HttpStatus.BAD_REQUEST).result(e.getMessage());
                }
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al actualizar el rol.");
            }
        });

        app.delete("/roles/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                repositorio.eliminar(id);
                ctx.status(HttpStatus.OK).result("Rol eliminado correctamente.");
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al eliminar el rol.");
            }
        });
    }
}