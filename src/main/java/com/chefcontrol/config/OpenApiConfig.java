package com.chefcontrol.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI chefControlOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Servidor Local");

        Server renderServer = new Server();
        renderServer.setUrl("https://chefcontrol-rf1o.onrender.com");
        renderServer.setDescription("Servidor de Producción (Render)");

        Contact contact = new Contact();
        contact.setName("ChefControl Team");
        contact.setEmail("soporte@chefcontrol.com");

        License license = new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");

        Info info = new Info()
                .title("ChefControl API")
                .version("1.0.0")
                .description("""
                        ## Sistema de Gestión de Restaurante
                        
                        API REST completa para la gestión integral de un restaurante, incluyendo:
                        
                        - 🍽️ **Productos** — Menú con categorías, precios y disponibilidad
                        - 📋 **Categorías** — Clasificación de productos
                        - 🪑 **Mesas** — Control de mesas y su estado en tiempo real
                        - 👥 **Clientes** — Registro y gestión de clientes
                        - 👨‍🍳 **Empleados** — Personal (meseros, cocineros, cajeros, etc.)
                        - 🧾 **Pedidos** — Creación y seguimiento de pedidos
                        - 🔧 **Configuración** — Parámetros del restaurante
                        """)
                .contact(contact)
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(renderServer, localServer));
    }
}
