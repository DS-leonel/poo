package com.tienda.controllers;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tienda.models.Categoria;
import com.tienda.repositories.CategoriaRepositorio;

public class CategoriaController {
    static CategoriaRepositorio repositorio = new CategoriaRepositorio();
    static Gson gson = new Gson();

    public static void init() {
        post("/categorias", (req, res) -> {
            try {
                Categoria categoria = gson.fromJson(req.body(), Categoria.class);
                if (categoria == null) {
                    res.status(400);
                    return gson.toJson("Error: Datos inválidos");
                }
                repositorio.agregar(categoria);
                res.status(201);
                return gson.toJson(categoria);
            } catch (Exception e) {
                res.status(500);
                return gson.toJson("Error interno del servidor");
            }
        });

        get("/categorias", (req, res) -> {
            res.type("application/json");
            return gson.toJson(repositorio.obtenerTodos());
        });

        get("/categorias/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                Categoria categoria = repositorio.obtener(id);
                if (categoria != null) {
                    res.type("application/json");
                    return gson.toJson(categoria);
                } else {
                    res.status(404);
                    return gson.toJson("Categoría no encontrada");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            }
        });

        put("/categorias/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                Categoria categoria = gson.fromJson(req.body(), Categoria.class);
                if (categoria == null) {
                    res.status(400);
                    return gson.toJson("Error: Datos inválidos");
                }
                Categoria categoriaActualizada = repositorio.actualizar(id, categoria);
                if (categoriaActualizada != null) {
                    res.type("application/json");
                    return gson.toJson(categoriaActualizada);
                } else {
                    res.status(404);
                    return gson.toJson("Categoría no encontrada");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            } catch (Exception e) {
                res.status(500);
                return gson.toJson("Error interno del servidor");
            }
        });

        delete("/categorias/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                boolean eliminado = repositorio.eliminar(id);
                if (eliminado) {
                    res.status(200);
                    return gson.toJson("Categoría eliminada");
                } else {
                    res.status(404);
                    return gson.toJson("Categoría no encontrada");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            }
        });
    }
}