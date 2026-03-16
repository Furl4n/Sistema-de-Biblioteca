package dev.PedroFurlan.Sistema_Biblioteca.DTO;

import dev.PedroFurlan.Sistema_Biblioteca.model.User.Role;

public record UserResponseDTO(String name, String email, Role role) {
}
