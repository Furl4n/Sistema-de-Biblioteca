package dev.PedroFurlan.Sistema_Biblioteca.DTO.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddUserRequestDTO(
        @Schema(
                description = "User name",
                example = "Pedro Furlan"
        )
        @NotBlank(message = "Name is required.")
        @Size(max = 100, message = "Name must have at most 100 characters.")
        String name,

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
        @Size(min = 8, max = 255,
                message = "Password must contain between 8 and 255 characters.")
        String password
) {
}