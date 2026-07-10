package dev.PedroFurlan.Sistema_Biblioteca.DTO.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @Schema(
                description = "User email",
                example = "email@email.com"
        )
        @NotBlank(message = "Email is required.")
        @Email(message = "Invalid email.")
        String email,

        @Schema(
                description = "User password",
                example = "PasSw0RD"
        )
        @NotBlank(message = "Password is required.")
        String password
) {
}
