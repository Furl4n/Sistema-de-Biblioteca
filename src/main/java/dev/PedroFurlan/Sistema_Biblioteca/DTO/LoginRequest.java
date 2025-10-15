package dev.PedroFurlan.Sistema_Biblioteca.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
