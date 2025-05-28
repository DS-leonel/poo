package com.tienda.controllers.facturas;

import io.javalin.Javalin;
import com.google.gson.Gson;
import com.tienda.models.facturas.Empleado;
import com.tienda.repositories.facturas.EmpleadoRepositorio;

public class EmpleadoController {
    static EmpleadoRepositorio repositorio = new EmpleadoRepositorio();
    static Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/empleados", ctx -> {
            Empleado empleado = gson.fromJson(ctx.body(), Empleado.class);
            repositorio.agregar(empleado);
            ctx.status(201).json(empleado);
        });

        app.get("/empleados", ctx -> ctx.json(repositorio.obtenerTodos()));

        app.get("/empleados/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(repositorio.obtener(id));
        });

        app.put("/empleados/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Empleado empleado = gson.fromJson(ctx.body(), Empleado.class);
            ctx.json(repositorio.actualizar(id, empleado));
        });

        app.delete("/empleados/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Empleado eliminado");
        });
    }
}
