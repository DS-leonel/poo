package com.tienda.controllers.seguridad;

import com.google.gson.Gson;
import com.tienda.models.seguridad.Usuario;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final Gson gson = new Gson();

    public static void init(Javalin app) {
        app.get("/usuarios", ctx -> {
            ctx.json(usuarios);
        });

        app.post("/usuarios", ctx -> {
            try {
                Usuario usuario = gson.fromJson(ctx.body(), Usuario.class);
                usuarios.add(usuario);
                ctx.status(HttpStatus.CREATED).json(usuario);
            } catch (Exception e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("Error al agregar el usuario.");
            }
        });

        app.get("/usuarios/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Usuario usuario = usuarios.stream()
                        .filter(u -> u.getId() == id)
                        .findFirst()
                        .orElse(null);
                if (usuario != null) {
                    ctx.json(usuario);
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Usuario no encontrado.");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            }
        });

        app.delete("/usuarios/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                boolean removed = usuarios.removeIf(u -> u.getId() == id);
                if (removed) {
                    ctx.status(HttpStatus.OK).result("Usuario eliminado correctamente.");
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Usuario no encontrado.");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            }
        });
    }
}