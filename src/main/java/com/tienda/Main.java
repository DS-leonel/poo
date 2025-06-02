package com.tienda;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

import com.tienda.controllers.facturas.DevolucionController;
import com.tienda.controllers.inventarios.LoteController;
import com.tienda.controllers.seguridad.RolController;
import com.tienda.controllers.inventarios.TransferenciaController;
import com.tienda.controllers.inventarios.ProveedorController;
import com.tienda.controllers.facturas.ClienteController;
import com.tienda.controllers.facturas.CategoriaController;
import com.tienda.controllers.descuentos.DetallePedidoController;
import com.tienda.controllers.facturas.EmpleadoController;
import com.tienda.controllers.facturas.PedidoController;
import com.tienda.controllers.inventarios.ProductoController;
import com.tienda.controllers.inventarios.SucursalController;
import com.tienda.controllers.inventarios.InventarioController;
import com.tienda.controllers.facturas.FacturaController;
import com.tienda.controllers.descuentos.DescuentoController;
import com.tienda.controllers.descuentos.PromocionController;
import com.tienda.controllers.seguridad.UsuarioController;
import com.tienda.controllers.inventarios.EnvioController;
import com.tienda.controllers.facturas.PagoController;
import com.tienda.controllers.facturas.ReportesController;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.jsonMapper(new JavalinJackson());
        }).start(4567);

        // Registrar todos los controladores
        CategoriaController.init(app);
        DetallePedidoController.init(app);
        EmpleadoController.init(app);
        PedidoController.init(app);
        ProductoController.init(app);
        ClienteController.init(app);
        ProveedorController.init(app);
        TransferenciaController.init(app);

        // Registrar controladores adicionales
        SucursalController.init(app);
        InventarioController.init(app);
        FacturaController.init(app);
        DescuentoController.init(app);
        RolController.init(app);
        LoteController.init(app);

        // Registrar los nuevos controladores
        PromocionController.init(app);
        UsuarioController.init(app);
        EnvioController.init(app);
        new PagoController().init(app);
        DevolucionController.init(app);
        ReportesController.init(app);

        System.out.println("Servidor corriendo en http://localhost:4567");
    }
}
