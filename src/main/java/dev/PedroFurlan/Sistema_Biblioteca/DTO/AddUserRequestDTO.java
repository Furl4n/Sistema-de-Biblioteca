package dev.PedroFurlan.Sistema_Biblioteca.DTO;

import dev.PedroFurlan.Sistema_Biblioteca.model.User.Role;

public record AddUserRequestDTO(String name, String email, String password, Role role) {
}
