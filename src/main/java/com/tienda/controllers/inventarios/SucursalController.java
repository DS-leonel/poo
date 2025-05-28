package com.tienda.controllers.inventarios;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.tienda.models.inventarios.Sucursal;
import com.tienda.repositories.inventarios.SucursalRepositorio;

public class SucursalController {
    static SucursalRepositorio repositorio = new SucursalRepositorio();
    static Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/sucursales", ctx -> {
            Sucursal sucursal = gson.fromJson(ctx.body(), Sucursal.class);
            repositorio.agregar(sucursal);
            ctx.status(201).json(sucursal);
        });

        app.get("/sucursales", ctx -> ctx.json(repositorio.obtenerTodos()));

        app.get("/sucursales/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(repositorio.obtener(id));
        });

        app.put("/sucursales/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Sucursal sucursal = gson.fromJson(ctx.body(), Sucursal.class);
            ctx.json(repositorio.actualizar(id, sucursal));
        });

        app.delete("/sucursales/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Sucursal eliminada");
        });
    }
}

