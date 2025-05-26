package com.tienda;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

import com.tienda.controllers.ClienteController;
import com.tienda.controllers.CategoriaController;
import com.tienda.controllers.DetallePedidoController;
import com.tienda.controllers.EmpleadoController;
import com.tienda.controllers.PedidoController;
import com.tienda.controllers.ProductoController;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.jsonMapper(new JavalinJackson()); // Usa Jackson para JSON
        }).start(4567);

        // Registrar todos los controladores
        ClienteController.init(app);
        CategoriaController.init(app);
        DetallePedidoController.init(app);
        EmpleadoController.init(app);
        PedidoController.init(app);
        ProductoController.init(app);

        System.out.println("Servidor corriendo en http://localhost:4567");
    }
}
