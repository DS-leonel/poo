package com.tienda;

import io.javalin.Javalin;
import com.tienda.controllers.CategoriaController;
import com.tienda.controllers.ClienteController;
import com.tienda.controllers.DetallePedidoController;
import com.tienda.controllers.EmpleadoController;
import com.tienda.controllers.PedidoController;
import com.tienda.controllers.ProductoController;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
        }).start(4567);

        CategoriaController.init(app);
        DetallePedidoController.init(app);
        ClienteController.init(app);
        ProductoController.init(app);
        PedidoController.init(app);
        EmpleadoController.init(app);
    }
}