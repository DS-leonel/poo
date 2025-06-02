package com.tienda.controllers.inventarios;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import com.google.gson.Gson;
import com.tienda.models.inventarios.Sucursal;
import com.tienda.repositories.inventarios.SucursalRepositorio;

public class SucursalController {
    private static final SucursalRepositorio repositorio = new SucursalRepositorio();
    private static final Gson gson = new Gson();

    public static void init(Javalin app) {

        app.post("/sucursales", ctx -> {
            try {
                Sucursal sucursal = gson.fromJson(ctx.body(), Sucursal.class);
                repositorio.agregar(sucursal);
                ctx.status(HttpStatus.CREATED).json(sucursal);
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error interno al crear la sucursal");
            }
        });

        app.get("/sucursales", ctx -> {
            try {
                ctx.json(repositorio.obtenerTodos());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error interno al obtener las sucursales");
            }
        });

        app.get("/sucursales/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Sucursal sucursal = repositorio.obtener(id);
                if (sucursal != null) {
                    ctx.json(sucursal);
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Sucursal no encontrada");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero");
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error interno al obtener la sucursal");
            }
        });

        app.put("/sucursales/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Sucursal sucursal = gson.fromJson(ctx.body(), Sucursal.class);
                Sucursal actualizado = repositorio.actualizar(id, sucursal);
                if (actualizado != null) {
                    ctx.json(actualizado);
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Sucursal no encontrada para actualizar");
                }
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero");
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error interno al actualizar la sucursal");
            }
        });

        app.delete("/sucursales/{id}", ctx -> {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                repositorio.eliminar(id);
                ctx.status(HttpStatus.OK).result("Sucursal eliminada correctamente");
            } catch (NumberFormatException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("El ID debe ser un número entero");
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Error interno al eliminar la sucursal");
            }
        });
    }
}
