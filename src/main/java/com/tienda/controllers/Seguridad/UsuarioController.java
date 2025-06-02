package com.tienda.controllers.seguridad;

import com.google.gson.Gson;
import com.tienda.models.seguridad.Usuario;
import com.tienda.repositories.seguridad.UsuarioRepositorio;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

import java.util.Locale;

public class UsuarioController {
    private static final UsuarioRepositorio repositorio = new UsuarioRepositorio();
    private static final Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/usuarios", ctx -> {
            try {
                Usuario usuario = gson.fromJson(ctx.body(), Usuario.class);
                repositorio.agregar(usuario);
                ctx.status(HttpStatus.CREATED).json(usuario);
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al agregar el usuario.");
            }
        });

        app.get("/usuarios", ctx -> {
            try {
                ctx.json(repositorio.obtenerTodos());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al obtener los usuarios.");
            }
        });

        app.get("/usuarios/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Usuario usuario = repositorio.obtener(id);
                ctx.json(usuario);
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al obtener el usuario.");
            }
        });

        app.put("/usuarios/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Usuario usuario = gson.fromJson(ctx.body(), Usuario.class);

                Usuario usuarioActualizado = repositorio.actualizar(id, usuario);
                ctx.json(usuarioActualizado);
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            } catch (IllegalArgumentException e) {
                String msg = e.getMessage() != null ? e.getMessage().toLowerCase(Locale.ROOT) : "";
                if (msg.contains("no encontrado")) {
                    ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
                } else {
                    ctx.status(HttpStatus.BAD_REQUEST).result(e.getMessage());
                }
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al actualizar el usuario.");
            }
        });

        app.delete("/usuarios/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                repositorio.eliminar(id);
                ctx.status(HttpStatus.OK).result("Usuario eliminado correctamente.");
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al eliminar el usuario.");
            }
        });
    }
}
