package com.tienda.controllers.facturas;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import com.google.gson.Gson;
import com.tienda.models.facturas.Empleado;
import com.tienda.repositories.facturas.EmpleadoRepositorio;

public class EmpleadoController {
    private static final EmpleadoRepositorio repositorio = new EmpleadoRepositorio();
    private static final Gson gson = new Gson();

    public static void init(Javalin app) {
        app.post("/empleados", EmpleadoController::crearEmpleado);
        app.get("/empleados", ctx -> ctx.json(repositorio.obtenerTodos()));
        app.get("/empleados/{id}", EmpleadoController::obtenerEmpleado);
        app.put("/empleados/{id}", EmpleadoController::actualizarEmpleado);
        app.delete("/empleados/{id}", EmpleadoController::eliminarEmpleado);
    }

    private static void crearEmpleado(Context ctx) {
        try {
            Empleado empleado = gson.fromJson(ctx.body(), Empleado.class);
            repositorio.agregar(empleado);
            ctx.status(HttpStatus.CREATED).json(empleado);
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("Error al crear empleado: " + e.getMessage());
        }
    }

    private static void obtenerEmpleado(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Empleado empleado = repositorio.obtener(id);
            ctx.json(empleado);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }

    private static void actualizarEmpleado(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Empleado empleado = gson.fromJson(ctx.body(), Empleado.class);
            Empleado actualizado = repositorio.actualizar(id, empleado);
            ctx.json(actualizado);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }

    private static void eliminarEmpleado(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repositorio.eliminar(id);
            ctx.result("Empleado eliminado");
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("ID inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.NOT_FOUND).result(e.getMessage());
        }
    }
}
