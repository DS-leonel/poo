package com.tienda.controllers.inventarios;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import com.google.gson.Gson;
import com.tienda.models.inventarios.Producto;
import com.tienda.repositories.inventarios.ProductoRepositorio;

public class ProductoController {
    private static final ProductoRepositorio repositorio = new ProductoRepositorio();
    private static final Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/productos", ctx -> {
            try {
                Producto producto = gson.fromJson(ctx.body(), Producto.class);
                repositorio.agregar(producto);
                ctx.status(HttpStatus.CREATED).json(producto);
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al agregar el producto.");
            }
        });

        app.get("/productos", ctx -> {
            ctx.json(repositorio.obtenerTodos());
        });

        app.get("/productos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Producto producto = repositorio.obtener(id);
                ctx.json(producto);
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al obtener el producto.");
            }
        });

        app.put("/productos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Producto producto = gson.fromJson(ctx.body(), Producto.class);
                Producto actualizado = repositorio.actualizar(id, producto);
                ctx.json(actualizado);
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            } catch (IllegalArgumentException e) {
                if (e.getMessage().contains("no encontrado")) {
                    ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
                } else {
                    ctx.status(HttpStatus.BAD_REQUEST).result(e.getMessage());
                }
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al actualizar el producto.");
            }
        });

        app.delete("/productos/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                repositorio.eliminar(id);
                ctx.status(HttpStatus.OK).result("Producto eliminado correctamente.");
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero.");
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error inesperado al eliminar el producto.");
            }
        });
    }
}
