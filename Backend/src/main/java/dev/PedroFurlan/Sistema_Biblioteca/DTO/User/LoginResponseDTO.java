package dev.PedroFurlan.Sistema_Biblioteca.DTO.User;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponseDTO(
        @Schema(
                description = "Token",
                example = "eyJhbGciOiJIFzI1EiJ9"
        )
        String token) {
}
