package dev.PedroFurlan.Sistema_Biblioteca.infra.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Library Management API",
                version = "2.0",
                description = "REST API for managing books, loans, reservations and users.",
                contact = @Contact(
                        name = "Pedro Furlan",
                        email = "pedrogfurlan1@gmail.com"
                ),
                license = @License(
                        name = "MIT"
                )
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {
}