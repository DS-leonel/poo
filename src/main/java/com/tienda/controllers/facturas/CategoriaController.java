package com.tienda.controllers.facturas;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import com.google.gson.Gson;
import com.tienda.models.facturas.Categoria;
import com.tienda.repositories.facturas.CategoriaRepositorio;

public class CategoriaController {
    private static final CategoriaRepositorio repositorio = new CategoriaRepositorio();
    private static final Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/categorias", CategoriaController::crearCategoria);
        app.get("/categorias", ctx -> ctx.json(repositorio.obtenerTodos()));
        app.get("/categorias/{id}", CategoriaController::obtenerCategoria);
        app.put("/categorias/{id}", CategoriaController::actualizarCategoria);
        app.delete("/categorias/{id}", CategoriaController::eliminarCategoria);
    }

    private static void crearCategoria(Context ctx) {
        try {
            Categoria categoria = gson.fromJson(ctx.body(), Categoria.class);
            repositorio.agregar(categoria);
            ctx.status(HttpStatus.CREATED).json(categoria);
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("Error al crear categoría: " + e.getMessage());
        }
    }

    private static void obtenerCategoria(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Categoria categoria = repositorio.obtener(id);
            ctx.json(categoria);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }

    private static void actualizarCategoria(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Categoria categoria = gson.fromJson(ctx.body(), Categoria.class);
            Categoria actualizada = repositorio.actualizar(id, categoria);
            ctx.json(actualizada);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }

    private static void eliminarCategoria(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Categoría eliminada");
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }
}
