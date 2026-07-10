package dev.PedroFurlan.Sistema_Biblioteca.DTO.User;

import dev.PedroFurlan.Sistema_Biblioteca.model.User.Role;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserResponseDTO(
        @Schema(
                description = "User name",
                example = "Pedro Furlan"
        )
        String name,

        @Schema(
                description = "User email",
                example = "email@email.com"
        )
        String email,

        @Schema(
                description = "User role",
                example = "READER"
        )
        Role role
) {
}
